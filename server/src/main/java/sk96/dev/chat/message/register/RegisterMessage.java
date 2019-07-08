package sk96.dev.chat.message.register;

import sk96.dev.chat.message.Message;

public class RegisterMessage extends Message {
    public final String username;
    public final String password;

    public RegisterMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
