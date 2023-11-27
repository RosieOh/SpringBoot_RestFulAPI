package com.pro06.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyDTO {
    private Long bno;

    private String title;

    private String content;

    private LocalDateTime regDate;

    private Long replyCount;
}
