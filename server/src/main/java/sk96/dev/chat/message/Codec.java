package sk96.dev.chat.message;

import sk96.dev.chat.message.chats.ChatMessageDecoder;
import sk96.dev.chat.message.chats.ChatMessageEncoder;
import sk96.dev.chat.message.chats.ChatMessageHandler;
import sk96.dev.chat.message.chats.ChatMessageResponse;
import sk96.dev.chat.message.contacts.*;
import sk96.dev.chat.message.login.LoginMessageDecoder;
import sk96.dev.chat.message.login.LoginMessageEncoder;
import sk96.dev.chat.message.login.LoginMessageHandler;
import sk96.dev.chat.message.login.LoginMessageResponse;

import java.util.HashMap;
import java.util.Map;

public final class Codec {
    public static final Map<Class<? extends Message>, MessageEncoder<?>> encoders = new HashMap<>();
    public static final Map<MessageType, MessageHandler<?>> handlers = new HashMap<>();
    public static final Map<MessageType, MessageDecoder<?>> decoders = new HashMap<>();
    static {
        handlers.put(MessageType.LOGIN, new LoginMessageHandler());
        decoders.put(MessageType.LOGIN, new LoginMessageDecoder());
        encoders.put(LoginMessageResponse.class, new LoginMessageEncoder());

        handlers.put(MessageType.CONTACTS, new ContactsMessageHandler());
        decoders.put(MessageType.CONTACTS, new ContactsMessageDecoder());
        encoders.put(ContactsMessageResponse.class, new ContactsMessageEncoder());

        handlers.put(MessageType.CHAT, new ChatMessageHandler());
        decoders.put(MessageType.CHAT, new ChatMessageDecoder());
        encoders.put(ChatMessageResponse.class, new ChatMessageEncoder());
    }
}