package com.jhin.boardproject.service.board;

import com.jhin.boardproject.mapper.board.BoardMapper;
import com.jhin.boardproject.model.dto.board.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDto> getBoardList() {
        List<BoardDto> list = boardMapper.getPosts();
        Collections.reverse(list);
        return list;
    }

    public BoardDto findPostById(int id){ return boardMapper.selectPostById(id); }

    public void postBoard(BoardDto boardDto){ boardMapper.insertPost(boardDto); }

    public void updatePost(BoardDto boardDto){ boardMapper.updatePost(boardDto); }

    public void deletePostById(int id) {boardMapper.deletePost(id);}
}
