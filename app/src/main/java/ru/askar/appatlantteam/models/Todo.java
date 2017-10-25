package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Todo implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String completed;

    public Todo(int userID, int ID, String title, String completed) {
        this.userId = userID;
        this.id = ID;
        this.title = title;
        this.completed = completed;
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

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public int hashCode() {
        return 9* userId + 11* id + 13*title.hashCode() + 17*completed.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Todo todo = (Todo) obj;
        if (userId != todo.userId || id != todo.id || !title.equals(todo.title) || !completed.equals(todo.completed))
            return false;
        return true;
    }
}
