package sk96.dev.chat.message.contacts;

public class Contact {
    public final int id;
    public final String username;
    public final String firstName;
    public final String lastName;
    public final String profilePicUrl;
    public final String status;
    public final boolean online;

    public Contact(int id, String username, String firstName, String lastName, String profilePicUrl, String status, boolean online) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicUrl = profilePicUrl;
        this.status = status;
        this.online = online;
    }
}