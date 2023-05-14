package spring.board.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import spring.board.comment.request.CommentReqeust;
import spring.board.comment.service.CommentService;
import spring.board.member.entity.Member;
import spring.board.member.service.MemberService;

@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final MemberService memberService;

    @PostMapping("/comment/insertComment")
    ResponseEntity<String> insertComment(CommentReqeust commentReqeust, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        int memberseq = (int)session.getAttribute("loginId");
        Member member = memberService.selectOneMemberBySeq(memberseq);

        commentService.saveComment(commentReqeust, member);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("댓글이 등록되었습니다.");
    }

    @PostMapping("/comment/modifyComment")
    ResponseEntity<String> modifyComment(CommentReqeust commentReqeust, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        int memberseq = (int)session.getAttribute("loginId");
        Member member = memberService.selectOneMemberBySeq(memberseq);

        commentService.saveComment(commentReqeust, member);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("댓글이 수정되었습니다.");
    }

    @PostMapping("/comment/deleteComment")
    ResponseEntity<String> deleteComment(CommentReqeust commentReqeust, HttpServletRequest request) throws Exception {
        commentService.deleteComment(commentReqeust);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("댓글이 삭제되었습니다.");
    }
}
