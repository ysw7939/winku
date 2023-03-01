package com.example.winku.domain.feed;

import com.example.winku.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Feed {
    private long id;
    private String loginId;
    private Date date;
    private String name;
    private String profile;
    private String imgPath;
    private String content;
    private int views;
    private int likes;
    private int dislike;

    private int comments;
    private List<Comment> commentList;

    public Feed() {
    }

    public Feed(String loginId, String name, String profile, String imgPath, String content) {
        this.loginId = loginId;
        this.name = name;
        this.profile = profile;
        this.imgPath = imgPath;
        this.content = content;
    }
}
