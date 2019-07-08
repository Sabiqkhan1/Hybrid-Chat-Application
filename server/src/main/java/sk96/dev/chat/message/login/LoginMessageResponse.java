package sk96.dev.chat.message.login;

import sk96.dev.chat.message.Message;

public class LoginMessageResponse extends Message {
    public final String username;
    public final boolean authenticated;
    public final String token;
    public final String reason;

    public LoginMessageResponse(String username, boolean authenticated, String token, String reason) {
        this.username = username;
        this.authenticated = authenticated;
        this.token = token;
        this.reason = reason;
    }
}
