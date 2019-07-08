package sk96.dev.chat.message;

import sk96.dev.chat.utils.JsonSearch;

public abstract class MessageDecoder<T extends Message> {
    /**
     * Decodes JSON data received from Kik into a suitable Message object.
     */
    public abstract T decode(String json);

    /**
     * Helper function to get a String value from a json by it's key
     */
    protected String getString(String json, String name) {
        if (JsonSearch.has(json, name)) {
            return JsonSearch.get(json, name);
        }
        throw new IllegalArgumentException("Cannot find " + name + " in json: " + json);
    }

    /**
     * Helper function to get a Boolean value from a json by it's key
     */
    protected boolean getBoolean(String json, String name) {
        return Boolean.parseBoolean(getString(json, name));
    }
}