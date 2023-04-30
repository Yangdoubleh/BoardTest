package spring.board.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.board.member.entity.Member;
import spring.board.member.service.MemberService;
import spring.board.member.request.MemberRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 메인 화면
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/user/main";
    }

    /**
     * 회원 등록
     * @param memberRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/insertMember")
    public ResponseEntity<Member> insertMember(MemberRequest memberRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.insertMember(memberRequest));
    }

    /**
     * 멤버 로그인
     * @param loginMember
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/doLogin")
    public ResponseEntity<Member> selectOneMemberById(MemberRequest loginMember, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        Member loginInfo = memberService.selectOneMemberById(loginMember);

        session.setMaxInactiveInterval(3600);
        session.setAttribute("loginId", loginInfo.getMemberseq());

        return ResponseEntity.ok(loginInfo);
    }

    /**
     * 로그인 화면
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginId");
        return "member/login";
    }

    /**
     * 등록 화면
     * @return
     */
    @RequestMapping(value = "/user/insert", method = RequestMethod.GET)
    public String insertPage() {
        return "member/insert";
    }
}
