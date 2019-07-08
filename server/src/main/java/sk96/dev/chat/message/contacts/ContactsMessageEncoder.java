package sk96.dev.chat.message.contacts;

import sk96.dev.chat.message.MessageEncoder;
import sk96.dev.chat.message.Packet;
import sk96.dev.chat.message.PacketBuilder;

public class ContactsMessageEncoder extends MessageEncoder<ContactsMessageResponse> {
    @Override
    public Packet encode(ContactsMessageResponse message) {

        final PacketBuilder builder = new PacketBuilder();

        builder.put("type", "contacts");

        final StringBuilder prods = new StringBuilder("[");

        for(int i = 0; i < message.contacts.size(); i++) {
            final Contact p = message.contacts.get(i);

            prods.append("{");
            prods.append("\"id\":\"").append(p.id).append("\",");
            prods.append("\"username\":\"").append(p.username).append("\",");
            prods.append("\"firstName\":\"").append(p.firstName).append("\",");
            prods.append("\"lastName\":\"").append(p.lastName).append("\",");
            prods.append("\"profilePicUrl\":\"").append(p.profilePicUrl).append("\",");
            prods.append("\"status\":\"").append(p.status).append("\",");
            prods.append("\"online\":\"").append(p.online).append("\"");

            if(i == (message.contacts.size() - 1)) {
                prods.append("}");
            } else {
                prods.append("},");
            }

        }

        prods.append("]");
        builder.put("contacts", prods.toString());

        return builder.toPacket();
    }
}
