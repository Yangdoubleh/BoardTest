package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import spring.board.request.MemberRequest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @BeforeEach
    public void before() {
        log.info("class = {}", "memberService");
    }

    @Test
    @Rollback(value = true)
    void insertMember() throws Exception {
        log.info("method = {}", "insertMember");

        MemberRequest member = MemberRequest.builder()
                .id("insertid")
                .password("insertpassword")
                .nickname("insertnickname")
                .build();

        log.info("생성한{}", member.toString());

        Member insertMember = memberService.insertMember(member);

        log.info("등록한{}", member.toString());

        assertThat(insertMember.getId()).isEqualTo(member.getId());
        assertThat(insertMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(insertMember.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    @Rollback(value = true)
    void selectMember() throws Exception {
        log.info("method = {}", "selectMember");

        MemberRequest member = MemberRequest.builder()
                .id("selectid")
                .password("selectpassword")
                .nickname("selectnickname")
                .build();

        log.info("생성한{}", member.toString());

        memberService.insertMember(member);
        Member selectMember = memberService.selectOneMemberById(member);

        log.info("선택한{}", selectMember.toString());

        assertThat(selectMember.getId()).isEqualTo(member.getId());
        assertThat(selectMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(selectMember.getNickname()).isEqualTo(member.getNickname());
    }
}
