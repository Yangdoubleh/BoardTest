package spring.board.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.board.board.Board;
import spring.board.board.request.BoardRequest;
import spring.board.board.service.BoardService;
import spring.board.member.Member;
import spring.board.member.service.MemberService;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final MemberService memberService;

    private final BoardService boardService;

    /**
     * 게시판 리스트 화면
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/board/main")
    public String boardList(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");

        Member loginMember = memberService.selectOneMemberBySeq(memberseq);
        model.addAttribute("loginMember", loginMember);

        return "/board/boardList";
    }

    /**
     * 게시판 등록 화면
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/board/create")
    public String boardCreate(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");

        Member loginMember = memberService.selectOneMemberBySeq(memberseq);
        model.addAttribute("loginMember", loginMember);

        return "/board/boardCreate";
    }

    /**
     * 게시판 등록
     * @param request
     * @param boardRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/board/insertboard")
    public ResponseEntity<Board> insertBoard(HttpServletRequest request, BoardRequest boardRequest) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int)session.getAttribute("loginId");
        Board board = boardService.insertBoard(memberseq, boardRequest);

        return ResponseEntity.ok(board);
    }
}