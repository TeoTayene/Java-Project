package main.modelPackage;

import java.sql.Date;
import java.time.LocalDate;

public class LikeModel {
    private Integer id;
    private Integer postLiked;
    private Integer likedBy;
    private Date date;
    private String username;
    private String postContent;

    public LikeModel(Integer id, Date date, String username, String postContent) {
        setId(id);
        setDate(date);
        setUsername(username);
        setPostContent(postContent);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostLiked() {
        return postLiked;
    }

    public void setPostLiked(Integer postLiked) {
        this.postLiked = postLiked;
    }

    public Integer getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Integer likedBy) {
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
