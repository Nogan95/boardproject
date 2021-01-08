package com.jhin.boardproject.mapper.board;

import com.jhin.boardproject.model.dto.board.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("Select * FROM board")
    List<BoardDto> getPosts();

    @Select("Select * FROM board WHERE postID = #{id}")
    BoardDto selectPostById(@Param("id") int id);

    @Insert("INSERT INTO board (memberId, memberName, postTitle,postContent) VALUES (#{boardDto.memberId},#{boardDto.memberName},#{boardDto.postTitle}, #{boardDto.postContent})")
    void insertPost(@Param("boardDto") BoardDto boardDto);

    @Update("UPDATE board SET postTitle=#{boardDto.postTitle}, postContent = #{boardDto.postContent} WHERE postId = #{boardDto.postId}")
    void updatePost(@Param("boardDto") BoardDto boardDto);

    @Delete("DELETE FROM board WHERE postID = #{id}")
    void deletePost(@Param("id") int id);
}
