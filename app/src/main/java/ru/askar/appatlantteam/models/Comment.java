package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Comment implements Serializable {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public Comment(int postID, int ID, String name, String email, String body) {
        this.postId = postID;
        this.id = ID;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return body;
    }

    public void setText(String body) {
        this.body = body;
    }

    @Override
    public int hashCode() {
        int hash = 9* postId + 11* id + 13*name.hashCode() + 17*email.hashCode() + 19*body.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Comment comment = (Comment) obj;
        if (postId != comment.postId || id != comment.id || !name.equals(comment.name) || !email.equals(comment.email) || !body.equals(comment.body))
            return false;
        return true;
    }
}
