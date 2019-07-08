package sk96.dev.chat.message.login;

import sk96.dev.chat.message.Message;

public class LoginMessage extends Message {
    public final String username;
    public final String password;

    public LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
