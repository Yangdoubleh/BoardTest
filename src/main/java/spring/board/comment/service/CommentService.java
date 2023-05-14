package spring.board.comment.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.board.entity.Board;
import spring.board.board.repository.BoardRepository;
import spring.board.comment.entity.Comment;
import spring.board.comment.repository.CommentRepository;
import spring.board.comment.request.CommentReqeust;
import spring.board.member.entity.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    /**
     * 댓글 등록
     * @param commentReqeust
     * @param member
     * @return
     */
    @Transactional
    public Comment saveComment(CommentReqeust commentReqeust, Member member) {
        Comment insertComment = this.buildComment(commentReqeust, member);

        return commentRepository.save(insertComment);
    }

    /**
     * 댓글 객체 생성
     * @param commentReqeust
     * @param member
     * @return
     */
    private Comment buildComment(CommentReqeust commentReqeust, Member member) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String now = localDateTime.format(dateTimeFormatter);

        Board boardInfo = boardRepository.findOneByBoardseq(commentReqeust.getBoardseq());

        return Comment.builder()
                .commentseq(commentReqeust.getCommentseq())
                .board(boardInfo)
                .contents(commentReqeust.getContents())
                .member(member)
                .indate(now)
                .build();
    }
}
