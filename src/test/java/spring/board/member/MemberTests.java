package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.board.BoardApplicationTests;

import static org.assertj.core.api.Assertions.*;

/**
 * Member 등록 및 로그인 테스트
 */
@SpringBootTest
@Transactional
@Slf4j
public class MemberTests extends BoardApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 멤버 등록 테스트
     */
    @Test
    public void memberInsert() {
        Member member = Member.builder()
                .id("testmember")
                .password("testpassword")
                .nickname("testnickname")
                .build();

        Member insertMember = memberRepository.save(member);

        log.info(insertMember.toString());

        assertThat(insertMember.getId()).isEqualTo(member.getId());
        assertThat(insertMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(insertMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(insertMember).isEqualTo(member);
    }
}
