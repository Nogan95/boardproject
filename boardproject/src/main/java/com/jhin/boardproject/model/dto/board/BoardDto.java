package com.jhin.boardproject.model.dto.board;

import lombok.Data;

@Data
public class BoardDto {
    private int postId;
    private int memberId;
    private String memberName;
    private String postTitle;
    private String postContent;
}
