package spring.board.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.board.member.Member;
import spring.board.member.service.MemberService;
import spring.board.member.request.MemberRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/user/main";
    }

    @PostMapping(value = "/user/insertMember")
    public ResponseEntity<Member> insertMember(MemberRequest memberRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.insertMember(memberRequest));
    }

    @PostMapping(value = "/user/doLogin")
    public ResponseEntity<Member> selectOneMemberById(MemberRequest loginMember, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        Member loginInfo = memberService.selectOneMemberById(loginMember);

        session.setMaxInactiveInterval(3600);
        session.setAttribute("loginId", loginInfo.getMemberseq());

        return ResponseEntity.ok(loginInfo);
    }

    @RequestMapping(value = "/user/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginId");
        return "member/login";
    }

    @RequestMapping(value = "/user/insert", method = RequestMethod.GET)
    public String insertPage() {
        return "member/insert";
    }
}
