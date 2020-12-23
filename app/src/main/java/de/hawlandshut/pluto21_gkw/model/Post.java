package de.hawlandshut.pluto21_gkw.model;

import com.google.firebase.database.DataSnapshot;

public class Post {
    public String uid;  // Technische ID des Senders
    public String author; // Name des Authors;
    public String title;
    public String body;
    public long timestamp;
    public String firebaseKey;

    public Post(String uid, String author, String title, String body, long timestamp, String firebaseKey) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.firebaseKey = firebaseKey;
    }

    public static Post getPostFromSnapShot(DataSnapshot dataSnapshot){
        String uid = (String) dataSnapshot.child("uid").getValue();
        String author = (String) dataSnapshot.child("author").getValue();
        String title = (String) dataSnapshot.child("title").getValue();
        String body = (String) dataSnapshot.child("body").getValue();
        long timestamp = (long) dataSnapshot.child("timestamp").getValue();
        String firebaseKey = (String) dataSnapshot.getKey();

        return new Post(uid, author, title, body, timestamp, firebaseKey);
    }
}

