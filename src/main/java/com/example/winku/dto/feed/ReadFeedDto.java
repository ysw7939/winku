package com.example.winku.dto.feed;

import com.example.winku.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class ReadFeedDto {
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

    public ReadFeedDto() {
    }

    public ReadFeedDto(long id,String loginId, Date date, String name, String profile, String imgPath, String content, int views, int likes, int dislike, int comments) {
        this.id = id;
        this.loginId = loginId;
        this.date = date;
        this.name = name;
        this.profile = profile;
        this.imgPath = imgPath;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.dislike = dislike;
        this.comments = comments;
    }
}
