package sk96.dev.chat.message;

public abstract class MessageHandler<T extends Message> {
    public abstract Message handle(T message);
}