package sk96.dev.chat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import sk96.dev.chat.message.*;
import sk96.dev.chat.utils.JsonSearch;

import java.util.logging.Logger;

public class HttpServerVerticle extends AbstractVerticle {
    private static final Logger L = Logger.getLogger(HttpServerVerticle.class.getName());
    private final int port;

    public HttpServerVerticle(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);
        final SockJSHandler sockJSHandler = SockJSHandler.create(vertx,
                new SockJSHandlerOptions()
                        .setHeartbeatInterval(2000)
        );

        sockJSHandler.socketHandler(sockJSSocket -> {
            L.info("Received HTTP request from address " + sockJSSocket.remoteAddress());

            sockJSSocket.handler(ctx -> vertx.executeBlocking(x -> {
                final String json = new String(ctx.getBytes());

                if(null == json) {
                    if(json.length() <= 0) {
                        L.warning("Received non-json message, ignoring.");
                    } else {
                        L.warning("Received unknown data: " + json);
                    }
                    x.fail("Could not parse data as JSON");
                    return;
                }

                System.out.println("Json= " + json);

                if(!JsonSearch.has(json, "type")) {
                    x.fail("Could not find type tag in received json.");
                    return;
                }

                MessageType type = null;

                final String requestType = JsonSearch.get(json, "type");

                for (MessageType messageType : MessageType.values()) {
                    if (messageType.toString().equalsIgnoreCase(requestType)) {
                        type = messageType;
                        break;
                    }
                }

                if (null == type) {
                    x.fail("No suitable message type found for: " + requestType);
                    return;
                }

                L.info("Received type: " + requestType);

                final MessageDecoder decoder = Codec.decoders.get(type);

                if (null == decoder) {
                    x.fail("No decoder found for type: " + type.toString());
                    return;
                }

                final MessageHandler handler = Codec.handlers.get(type);

                if (null == handler) {
                    x.fail("No handler found for type: " + type.toString());
                    return;
                }

                L.info("Decoding message");

                final Message decodedMessage = decoder.decode(json);

                if (null == decodedMessage) {
                    x.fail("Failed to decode json: " + json);
                    return;
                }

                L.info("Decoded message");

                final Message message = handler.handle(decodedMessage);

                if (null == message) {
                    x.fail("Null Message object received from MessageHandler");
                    return;
                }

                L.info("Handled message");

                final MessageEncoder encoder = Codec.encoders.get(message.getClass());

                if (null == encoder) {
                    x.fail("Failed to encode " + message.getClass());
                    return;
                }

                L.info("Encoded message");

                final String encodedJson = encoder.encode(message).payload;

                L.info("Sending: " + encodedJson);

                sockJSSocket.write(encodedJson);
                x.complete();

            }, res -> {
                if (res.failed()) {
                    L.warning("Failed to handle HTTP request: " + res.cause().getMessage());
                } else if (res.succeeded()) {
                    L.info("Successfully handled HTTP request");
                }
            }));
        });

        router.route("/myapp/*").handler(sockJSHandler);

        vertx.executeBlocking(x -> server.requestHandler(router::accept).listen(port, res -> {
            if (res.succeeded()) {
                L.info("HTTP server online port " + port);
                x.complete();
            } else {
                L.severe("Could not start HTTP server on port " + port + ", cause: " + res.cause().getMessage());
                x.fail(res.cause());
            }
        }), res -> {
            if (res.failed()) {
                L.severe("Failed to load HTTP server");
            } else if (res.succeeded()) {
                L.info("HTTP server has finished loading");
            }
        });
    }
}
