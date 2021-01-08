package com.jhin.boardproject.service.board;

import com.jhin.boardproject.mapper.board.CommentMapper;
import com.jhin.boardproject.model.dto.board.BoardDto;
import com.jhin.boardproject.model.dto.board.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Cacheable(value="comment")
    public List<CommentDto> getCommentsByPostId(int id){
        List<CommentDto> list = commentMapper.getCommentsByPostId(id);
        Collections.reverse(list);
        return list;
    }

    @CacheEvict(value="comment", allEntries=true)
    public void insertComment(CommentDto commentDto)
    {
        commentMapper.insertComment(commentDto);
    }

}
