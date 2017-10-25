package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Post implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(int userID, int ID, String title, String body) {
        this.userId = userID;
        this.id = ID;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return body;
    }

    public void setText(String text) {
        this.body = text;
    }

    @Override
    public int hashCode() {
        return 9* userId + 11* id + 13*title.hashCode() + 17*body.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Post post = (Post) obj;
        if (userId != post.userId || id != post.id || !title.equals(post.title) || !body.equals(post.body))
            return false;
        return true;
    }
}
