package com.jhin.boardproject.controller.board;

import com.jhin.boardproject.model.dto.board.BoardDto;
import com.jhin.boardproject.model.dto.account.MemberDto;
import com.jhin.boardproject.service.board.BoardService;
import com.jhin.boardproject.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;

    @GetMapping("/list")
    public String getBoardList( Model model) {
        model.addAttribute("boardList", boardService.getBoardList());

        return "/service/board/list";
    }

    @GetMapping({"", "/"})
    public String boardForm(@RequestParam(value = "idx", defaultValue = "0") int idx, Model model) {
        model.addAttribute("board", boardService.findPostById(idx));
        model.addAttribute("commentList", commentService.getCommentsByPostId(idx));

        return "/service/board/form";
    }

    // 글쓰기
    @PostMapping("")
    public ResponseEntity<?> postBoard (HttpServletRequest request, @RequestBody BoardDto boardDto) throws Exception{
        HttpSession session = request.getSession();
        MemberDto member = (MemberDto)session.getAttribute("authInfo");

        boardDto.setMemberId(member.getMemberId());
        boardDto.setMemberName(member.getMemberName());
        boardService.postBoard(boardDto);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    // 수정
    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx") int idx, @RequestBody BoardDto boardDto) {
        BoardDto updateBoard = boardService.findPostById(idx);
        updateBoard.setPostTitle(boardDto.getPostTitle());
        updateBoard.setPostContent(boardDto.getPostContent());
        boardService.updatePost(updateBoard);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx") int idx) {
        boardService.deletePostById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
