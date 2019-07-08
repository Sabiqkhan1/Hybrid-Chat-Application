package sk96.dev.chat.message;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to build a packets payload.
 * Packet payload is a JSON formatted String so this class
 * uses a Map to create the Key/Value pair.
 */
public class PacketBuilder {
    private final Map<String, String> out = new HashMap<>();

    public PacketBuilder() {
    }

    /**
     * Adds a key/value pair to the JSON payload
     *
     * @param key   The identifier of a value
     * @param value The value to add into the message
     */
    public void put(String key, Object value) {
        out.put(key, String.valueOf(value));
    }

    /**
     * Converts the KVP to a String and creates a Packet object
     *
     * @return The Packet object containing the payload
     */
    public Packet toPacket() {
        return new Packet(new Gson().toJson(out));
    }
}
