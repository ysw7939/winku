package com.example.winku.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Recomment {
    private long id;

    private String loginId;
    private long commentId;
    private Date date;
    private String content;
    private String userName;
    private String profile;

    public Recomment() {
    }

    public Recomment(long commentId,String loginId, String content, String userName, String profile) {
        this.commentId = commentId;
        this.loginId = loginId;
        this.content = content;
        this.userName = userName;
        this.profile = profile;
    }
}
