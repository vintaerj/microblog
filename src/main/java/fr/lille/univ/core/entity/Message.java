package fr.lille.univ.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString()
@AllArgsConstructor()
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
    @Getter
    @Setter
    private String userName;


}
