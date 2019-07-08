package sk96.dev.chat.utils;

public class JsonSearch {
    public static boolean has(String json, String key) {
        return json.contains("\"" + key + "\":");
    }

    public static String get(final String json, String key) {
        final String formattedKey = "\"" + key + "\":";
        final int start = json.indexOf(formattedKey);
        return start < 0 ? "-1" : json.substring(start)
                .replace(formattedKey, "")
                .trim()
                .split("\"")[1];
    }
}