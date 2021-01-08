package com.jhin.boardproject.controller.board;

import com.jhin.boardproject.model.dto.account.MemberDto;
import com.jhin.boardproject.model.dto.board.CommentDto;
import com.jhin.boardproject.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("board/comment")
    public ResponseEntity<?> postBoard (HttpServletRequest request, @RequestBody CommentDto commentDto) throws Exception{
        HttpSession session = request.getSession();
        MemberDto member = (MemberDto)session.getAttribute("authInfo");
        commentDto.setMemberName(member.getMemberName());
        commentService.insertComment(commentDto);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
