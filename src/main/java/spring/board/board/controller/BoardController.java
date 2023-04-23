package spring.board.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/board/main", method = RequestMethod.GET)
    public String boardList(HttpServletRequest request, Model model,
            @PageableDefault(size = 10, sort = "boardseq",direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");

        Page<Board> boardList = boardService.findAll(pageable);

        Member loginMember = memberService.selectOneMemberBySeq(memberseq);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("boardList", boardList.toList());

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

    @RequestMapping(value = "/board/detailBoard", method = RequestMethod.GET)
    public String detailBoard(HttpServletRequest request, @RequestParam(value = "boardseq") int boardseq, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");
        Member loginMember = memberService.selectOneMemberBySeq(memberseq);

        Board detailBoard = boardService.findOneByBoardseq(boardseq);

        model.addAttribute("loginMember", loginMember);
        model.addAttribute("detailBoard", detailBoard);

        return "/board/boardDetail";
    }

    @PostMapping("/board/deleteBoard")
    public ResponseEntity<String> deleteBoard(HttpServletRequest request, BoardRequest boardRequest) {

        boardService.deleteById(boardRequest);

        String msg = "삭제되었습니다";

        return ResponseEntity.ok(msg);
    }
}