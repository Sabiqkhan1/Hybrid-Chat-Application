package sk96.dev.chat.message.chats;

import sk96.dev.chat.message.MessageEncoder;
import sk96.dev.chat.message.Packet;
import sk96.dev.chat.message.PacketBuilder;

import java.util.List;

public class ChatMessageEncoder extends MessageEncoder<ChatMessageResponse> {

    @Override
    public Packet encode(ChatMessageResponse message) {
        PacketBuilder builder = new PacketBuilder();
        List<Chat[]> chats = message.chats;

        builder.put("type", "chat");

        final StringBuilder prods = new StringBuilder("[");

        /* Chat with each person */
        for(int i = 0; i < chats.size(); i++) {
            Chat[] person  = chats.get(i);

            prods.append("{");

            boolean headers = false;

            /* Each message in that chat */
            for(int j = 0; j < person.length; j++) {
                Chat c = person[j];

                if(!headers) {
                    prods.append("\"id\":\"").append(c.id).append("\",");
                    prods.append("\"username\":\"").append(c.recipient).append("\",");
                    prods.append("\"messages\": [");
                    headers = true;
                }

                prods.append("{");
                prods.append("\"timestamp\":\"").append(c.timestamp).append("\",");
                prods.append("\"read\":\"").append(c.read).append("\",");
                prods.append("\"message\":\"").append(c.message).append("\"");
                if(j == (person.length - 1)) {
                    prods.append("}");
                } else {
                    prods.append("},");
                }
            }

            prods.append("]");
            if(i == (message.chats.size() - 1)) {
                prods.append("}");
            } else {
                prods.append("},");
            }
        }

        prods.append("]");
        builder.put("chats", prods.toString());
        return builder.toPacket();
    }
}
