package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Photo implements Serializable {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo(int albumID, int ID, String title, String url, String thumbnailUrl) {
        this.albumId = albumID;
        this.id = ID;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int hashCode() {
        return (9* albumId + 11* id + 13*title.hashCode() + 17*url.hashCode() + 19*thumbnailUrl.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Photo photo = (Photo) obj;
        if (albumId != photo.albumId || id != photo.id || !title.equals(photo.title) || !url.equals(photo.url) || !thumbnailUrl.equals(photo.thumbnailUrl))
            return false;
        return true;
    }
}
