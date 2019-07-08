package sk96.dev.chat.message.chats;

public class Chat {
    public final int id;
    public final String recipient;
    public final String timestamp;
    public final String message;
    public final boolean read;

    public Chat(int id, String recipient, String timestamp, String message, boolean read) {
        this.id = id;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.message = message;
        this.read = read;
    }
}