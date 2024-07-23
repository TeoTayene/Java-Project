package main.modelPackage;

import java.sql.Date;

public class LikeModel {
    private int id;
    private int postLiked;
    private int likedBy;
    private Date date;
    private String username;
    private String postContent;

    public LikeModel(int id, Date date, String username, String postContent) {
        setId(id);
        setDate(date);
        setUsername(username);
        setPostContent(postContent);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostLiked() {
        return postLiked;
    }

    public void setPostLiked(int postLiked) {
        this.postLiked = postLiked;
    }

    public int getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(int likedBy) {
        this.likedBy = likedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
