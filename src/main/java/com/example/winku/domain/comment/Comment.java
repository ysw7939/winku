package com.example.winku.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Comment {
    private long id;
    private long feedId;

    private Date date;
    private String userName;
    private String content;

    private String profile;

    private List<Recomment> recommentList;

    public Comment() {
    }

    public Comment(long feedId, String userName, String content, String profile) {
        this.feedId = feedId;
        this.userName = userName;
        this.content = content;
        this.profile = profile;
    }

}
