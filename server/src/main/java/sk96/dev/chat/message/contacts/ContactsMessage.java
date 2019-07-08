package sk96.dev.chat.message.contacts;

import sk96.dev.chat.message.Message;

public class ContactsMessage extends Message {
    public final String username;
    public final String token;

    public ContactsMessage(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
