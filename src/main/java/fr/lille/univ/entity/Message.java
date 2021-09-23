package fr.lille.univ.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Message {

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private Date publishedDate;
    @Getter
    @Setter
    private String replyId;
    @Getter
    @Setter
    private String userId;

}
