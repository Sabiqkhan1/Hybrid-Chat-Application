package sk96.dev.chat.message.chats;

import sk96.dev.chat.message.MessageDecoder;

public class ChatMessageDecoder extends MessageDecoder<ChatMessage> {

    @Override
    public ChatMessage decode(String json) {
        try {
            final int id = Integer.parseInt(getString(json, "id"));
            final String username = getString(json, "username");
            final String token = getString(json, "token");
            return new ChatMessage(id, username, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
