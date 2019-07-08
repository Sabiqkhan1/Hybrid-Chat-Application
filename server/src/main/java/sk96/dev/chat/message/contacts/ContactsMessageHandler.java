package sk96.dev.chat.message.contacts;

import sk96.dev.chat.message.Message;
import sk96.dev.chat.message.MessageHandler;

import java.util.ArrayList;
import java.util.List;

public class ContactsMessageHandler extends MessageHandler<ContactsMessage> {

    @Override
    public Message handle(ContactsMessage message) {

        //TODO: check if token is valid

        final String username = message.username;
        final String token = message.token;
        final String magic = username + "@" + token;

        //TODO: Sort contacts alphabetically by firstName

        final List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(
                1,
                "testUsername",
                "John",
                "Doe",
                "noProfilePicUrl",
                "Hey, I'm new!",
                false
        ));

        contacts.add(new Contact(
                2,
                "helloUsername",
                "Jane",
                "Doe",
                "noProfilePicUrl",
                "Hey, I'm Jane!",
                true
        ));

        return new ContactsMessageResponse(contacts);
    }
}