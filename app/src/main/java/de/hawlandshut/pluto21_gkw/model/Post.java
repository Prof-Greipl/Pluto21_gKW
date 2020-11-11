package de.hawlandshut.pluto21_gkw.model;

public class Post {
    public String uid;  // Technische ID des Senders
    public String author; // Name des Authors;
    public String title;
    public String body;
    public long timestamp;

    public Post(String uid, String author, String title, String body, long timestamp) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }
}
