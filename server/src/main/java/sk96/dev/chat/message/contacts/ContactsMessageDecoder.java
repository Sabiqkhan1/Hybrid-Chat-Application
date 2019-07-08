package sk96.dev.chat.message.contacts;

import sk96.dev.chat.message.MessageDecoder;

public class ContactsMessageDecoder extends MessageDecoder<ContactsMessage> {

    @Override
    public ContactsMessage decode(String json) {
        final String username = getString(json, "username");
        final String token = getString(json, "token");
        return new ContactsMessage(username, token);
    }
}
