package sk96.dev.chat.message.login;

import sk96.dev.chat.message.MessageEncoder;
import sk96.dev.chat.message.Packet;
import sk96.dev.chat.message.PacketBuilder;

public class LoginMessageEncoder extends MessageEncoder<LoginMessageResponse> {

    @Override
    public Packet encode(LoginMessageResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("type", "loginResponse");
        builder.put("username", message.username);
        builder.put("authenticated", message.authenticated);
        builder.put("token", message.token);
        builder.put("reason", message.reason);

        return builder.toPacket();

    }
}
