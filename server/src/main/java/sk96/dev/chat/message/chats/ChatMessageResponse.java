package sk96.dev.chat.message.chats;

import sk96.dev.chat.message.Message;

import java.util.List;

public class ChatMessageResponse extends Message {
    public final List<Chat[]> chats;

    public ChatMessageResponse(List<Chat[]> chats) {
        this.chats = chats;
    }
}
