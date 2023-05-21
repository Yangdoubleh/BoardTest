package spring.board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.board.board.entity.Board;
import spring.board.board.request.BoardRequest;
import spring.board.board.service.BoardService;
import spring.board.member.entity.Member;
import spring.board.member.service.MemberService;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Board", description = "게시판 API Document")
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
    @Operation(summary = "게시판 화면", description = "게시판 화면을 출력합니다.", tags = {"View"})
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
    @RequestMapping(value = "/board/create", method = RequestMethod.POST)
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

    /**
     * 게시글 상세보기
     * @param request
     * @param boardseq
     * @param model
     * @return
     * @throws Exception
     */
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

    /**
     * 게시글 수정 화면
     * @param request
     * @param boardseq
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/board/modifyBoard", method = RequestMethod.GET)
    public String modifyBoard(HttpServletRequest request, @RequestParam(value = "boardseq") int boardseq, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");
        Member loginMember = memberService.selectOneMemberBySeq(memberseq);

        Board detailBoard = boardService.findOneByBoardseq(boardseq);

        model.addAttribute("loginMember", loginMember);
        model.addAttribute("detailBoard", detailBoard);

        return "/board/boardModify";
    }

    /**
     * 게시글 삭제
     * @param request
     * @param boardRequest
     * @return
     */
    @PostMapping("/board/deleteBoard")
    public ResponseEntity<String> deleteBoard(HttpServletRequest request, BoardRequest boardRequest) {

        boardService.deleteById(boardRequest);

        String msg = "삭제되었습니다";

        return ResponseEntity.ok(msg);
    }

    /**
     * 게시글 수정
     * @param request
     * @param boardRequest
     * @return
     */
    @PostMapping("/board/updateBoard")
    public ResponseEntity<Board> updateBoard(HttpServletRequest request, BoardRequest boardRequest) {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");
        Board board = boardService.updateBoard(memberseq, boardRequest);

        return ResponseEntity.ok(board);
    }
}