package sk96.dev.chat.message.chats;

import sk96.dev.chat.message.Message;

public class ChatMessage extends Message {
    public final int id;
    public final String username;
    public final String token;

    public ChatMessage(int id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }
}
