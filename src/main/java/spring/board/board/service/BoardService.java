package spring.board.board.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.board.Board;
import spring.board.board.repository.BoardRepository;
import spring.board.board.request.BoardRequest;
import spring.board.member.Member;
import spring.board.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    /**
     * 게시판 등록
     * @param memberseq
     * @param boardRequest
     */
    @Transactional
    public Board insertBoard(int memberseq, BoardRequest boardRequest){
        Member member = this.getmember(memberseq);

        Board board = this.buildBoard(boardRequest, member);

        return boardRepository.save(board);
    }

    /**
     * 멤버 정보 가져오기
     * @param memberseq
     * @return
     */
    private Member getmember(int memberseq) {
        try {
            return memberRepository.findOneByMemberseq(memberseq);
        } catch (Exception e) {
            new Exception("no search member");
        }
        return null;
    }

    /**
     * board 객체 생성
     * @param boardRequest
     * @param member
     * @return
     */
    private Board buildBoard(BoardRequest boardRequest, Member member) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String now = localDateTime.format(dateTimeFormatter);

        return Board.builder()
                .member(member)
                .title(boardRequest.getTitle())
                .contents(boardRequest.getContents())
                .indate(now)
                .build();
    }

    /**
     * 게시판 페이징 조회
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    /**
     * 게시물 조회
     * @param boardseq
     * @return
     */
    @Transactional(readOnly = true)
    public Board findOneByBoardseq(int boardseq) {
        return boardRepository.findOneByBoardseq(boardseq);
    }


    @Transactional
    public void deleteById(BoardRequest boardRequest) {
        int boardseq = boardRequest.getBoardseq();
        boardRepository.deleteById(boardseq);
    }
}
