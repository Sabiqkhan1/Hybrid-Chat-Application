package sk96.dev.chat.message.login;

import sk96.dev.chat.message.Message;
import sk96.dev.chat.message.MessageHandler;

public class LoginMessageHandler extends MessageHandler<LoginMessage> {

    @Override
    public Message handle(LoginMessage message) {
        return new LoginMessageResponse(message.username, true, "1234", "Login OK");
    }
}
