package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import spring.board.request.MemberRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class MemberControllerTests {

    @Autowired
    MemberController memberController;

    @DisplayName("회원가입")
    @Test
    void SignUpTest() {
        //given
        MemberRequest member = member();

        ResponseEntity<Member> response = memberController.insertMember(member);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(member.getId());
        assertThat(response.getBody().getNickname()).isEqualTo(member.getNickname());
        assertThat(response.getBody().getPassword()).isEqualTo(member.getPassword());

    }

    private MemberRequest member() {
        return MemberRequest.builder()
                .id("testid")
                .password("testpw")
                .nickname("testname")
                .build();
    }

    @DisplayName("로그인")
    @Test
    void Logintest() {
        MemberRequest member = member();

        memberController.insertMember(member);

        ResponseEntity<Member> response = memberController.selectOneMemberById(member);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(member.getId());
        assertThat(response.getBody().getPassword()).isEqualTo(member.getPassword());
        assertThat(response.getBody().getNickname()).isEqualTo(member.getNickname());
    }
}
