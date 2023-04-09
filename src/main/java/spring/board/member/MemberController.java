package spring.board.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/user/insertMember", method= RequestMethod.POST)
    public ResponseEntity<Member> insertMember(@RequestBody Member insertMember) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.insertMember(insertMember));
    }

    @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
    public ResponseEntity<Member> selectOneMemberById(@RequestBody Member loginMember) {
        return ResponseEntity.ok(memberService.selectOneMemberById(loginMember));
    }

    @RequestMapping(value = "/user/main", method = RequestMethod.GET)
    public String main() {
        return "login";
    }
}
