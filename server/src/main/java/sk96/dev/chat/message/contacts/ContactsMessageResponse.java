package sk96.dev.chat.message.contacts;

import sk96.dev.chat.message.Message;

import java.util.List;

public class ContactsMessageResponse extends Message {
    public final List<Contact> contacts;

    public ContactsMessageResponse(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
