package spring.board.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.board.request.MemberRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/user/insertMember")
    public ResponseEntity<Member> insertMember(MemberRequest memberRequest) {
        log.info("insertMember ={}", memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.insertMember(memberRequest));
    }

    @PostMapping(value = "/user/doLogin")
    public ResponseEntity<Member> selectOneMemberById(MemberRequest loginMember) {
        log.info("loginMember ={}", loginMember.toString());
        return ResponseEntity.ok(memberService.selectOneMemberById(loginMember));
    }

    @RequestMapping(value = "/user/main", method = RequestMethod.GET)
    public String main() {
        return "login";
    }

    @RequestMapping(value = "/user/insert", method = RequestMethod.GET)
    public String insertPage() {
        return "insert";
    }
}
