package sk96.dev.chat.message;

public class Packet {
    /**
     * The data the Packet contains to be sent out.
     * This should always be a valid JSON String.
     */
    public final String payload;

    public Packet(String payload) {
        //The current framework in place doesn't really format the Arrays inside the JSON properly.
        //This fixes that so Arrays are displayed correctly in the JSON String.
        this.payload = payload.replaceAll("\"\\[", "[")
                .replaceAll("]\"", "]")
                .replaceAll("\\\\", "")
                .trim();
    }
}