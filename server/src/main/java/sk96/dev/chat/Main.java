package sk96.dev.chat;

import io.vertx.core.Vertx;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running");
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle(8080));
    }

}
