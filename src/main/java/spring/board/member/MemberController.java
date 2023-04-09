package spring.board.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/user/insertMember")
    public ResponseEntity<Member> insertMember(@RequestBody Member insertMember) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.insertMember(insertMember));
    }

    @PostMapping("/user/doLogin")
    public ResponseEntity<Member> selectOneMemberById(@RequestBody Member loginMember) {
        return ResponseEntity.ok(memberService.selectOneMemberById(loginMember));
    }
}
