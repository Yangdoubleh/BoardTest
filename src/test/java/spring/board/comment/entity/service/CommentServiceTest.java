package spring.board.comment.entity.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.board.board.entity.Board;
import spring.board.board.repository.BoardRepository;
import spring.board.comment.entity.Comment;
import spring.board.comment.request.CommentReqeust;
import spring.board.comment.service.CommentService;
import spring.board.member.entity.Member;
import spring.board.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CommentService commentService;

    @Test
    @Transactional
    void insertComment() {
        Board board = boardRepository.findAll().stream()
                .findFirst()
                .get();

        CommentReqeust commentReqeust = CommentReqeust.builder()
                .boardseq(board.getBoardseq())
                .contents("테스트댓글작성")
                .build();

        Member member = memberRepository.findAll().stream()
                .findFirst()
                .get();

        Comment insertComment = commentService.insertComment(commentReqeust, member);

        assertThat(insertComment.getBoard()).isEqualTo(board);
        assertThat(insertComment.getContents()).isEqualTo(commentReqeust.getContents());
        assertThat(insertComment.getMember()).isEqualTo(member);
    }
}