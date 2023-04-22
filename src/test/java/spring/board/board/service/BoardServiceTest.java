package spring.board.board.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.board.board.Board;
import spring.board.board.request.BoardRequest;
import spring.board.member.service.MemberService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void insertBoard() throws Exception {
        int memberseq = 4;
        BoardRequest boardRequest = BoardRequest.builder()
                .title("테스트타이틀")
                .contents("테스트콘텐츠")
                .build();

        Board board = boardService.insertBoard(memberseq, boardRequest);

        assertThat(board.getTitle()).isEqualTo(boardRequest.getTitle());
        assertThat(board.getContents()).isEqualTo(boardRequest.getContents());
        assertThat(board.getMember()).isEqualTo(memberService.selectOneMemberBySeq(memberseq));
    }
}