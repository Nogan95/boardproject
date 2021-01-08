package com.jhin.boardproject.mapper.board;

import com.jhin.boardproject.model.dto.board.BoardDto;
import com.jhin.boardproject.model.dto.board.CommentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comment WHERE postId = #{id}")
    List<CommentDto> getCommentsByPostId(@Param("id") int id);

    @Insert("INSERT INTO comment (postId, commentContent, memberName) VALUES (#{commentDto.postId}, #{commentDto.commentContent}, #{commentDto.memberName})")
    void insertComment(@Param("commentDto") CommentDto commentDto);
}
