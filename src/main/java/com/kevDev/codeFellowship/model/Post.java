package com.kevDev.codeFellowship.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String body;
    Timestamp createdAt;

    @ManyToOne
    AppUser postsOfUser;

    public Post() {
        // default constructor
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getPostsOfUser() {
        return postsOfUser;
    }

    public void setPostsOfUser(AppUser postsOfUser) {
        this.postsOfUser = postsOfUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "body='" + body + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
