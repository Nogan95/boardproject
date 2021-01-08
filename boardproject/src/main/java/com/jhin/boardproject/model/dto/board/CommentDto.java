package com.jhin.boardproject.model.dto.board;

import lombok.Data;

@Data
public class CommentDto {
    private int commentId;
    private int postId;
    private String memberName;
    private String CommentContent;
}
