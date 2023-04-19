package spring.board.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.board.member.Member;
import spring.board.member.service.MemberService;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final MemberService memberService;

    @RequestMapping("/board/main")
    public String boardList(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");

        Member loginMember = memberService.selectOneMemberBySeq(memberseq);
        model.addAttribute("loginMember", loginMember);

        return "/board/boardList";
    }

    @RequestMapping("/board/create")
    public String boardCreate(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();

        int memberseq = (int) session.getAttribute("loginId");

        Member loginMember = memberService.selectOneMemberBySeq(memberseq);
        model.addAttribute("loginMember", loginMember);

        return "/board/boardCreate";
    }
}