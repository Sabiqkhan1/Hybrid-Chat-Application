package sk96.dev.chat.message.login;

import sk96.dev.chat.message.MessageDecoder;

public class LoginMessageDecoder extends MessageDecoder<LoginMessage> {

    @Override
    public LoginMessage decode(String json) {
        final String username = getString(json, "username");
        final String password = getString(json, "password");
        return new LoginMessage(username, password);
    }
}
