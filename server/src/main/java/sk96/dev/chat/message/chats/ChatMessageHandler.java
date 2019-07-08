package sk96.dev.chat.message.chats;

import sk96.dev.chat.message.Message;
import sk96.dev.chat.message.MessageHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageHandler extends MessageHandler<ChatMessage> {

    @Override
    public Message handle(ChatMessage message) {

        //TODO: check if token is valid


        List<Chat[]> chats = new ArrayList<>();


        Chat[] chatWithJohn = {
                new Chat(0, "john", "1234", "Hello, World!", false),
                new Chat(1, "john", "1234", "yo??", false)
        };

        chats.add(chatWithJohn);
        return new ChatMessageResponse(chats);
    }
}
