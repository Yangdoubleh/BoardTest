package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import spring.board.BoardApplicationTests;

import static org.assertj.core.api.Assertions.*;

/**
 * Member 등록 및 로그인 테스트
 */
@SpringBootTest
@Transactional
@Slf4j
public class MemberRepositoryTests extends BoardApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 멤버 등록 테스트
     */
    @Test
    @Rollback(value = true)
    public void insertMember() {
        Member member = Member.builder()
                .id("testmember")
                .password("testpassword")
                .nickname("testnickname")
                .build();

        log.info("생성한{}", member.toString());

        Member insertMember = memberRepository.save(member);

        log.info("등록한{}", insertMember.toString());

        assertThat(insertMember.getId()).isEqualTo(member.getId());
        assertThat(insertMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(insertMember.getNickname()).isEqualTo(member.getNickname());
    }

    /**
     * 멤버 조회 테스트
     */
    @Test
    @Rollback(value = true)
    public void selectMember() {
        Member member = Member.builder()
                .id("testmember")
                .password("testpassword")
                .nickname("testnickname")
                .build();

        log.info("생성한{}", member.toString());

        memberRepository.save(member);

        Member selectMember = memberRepository.findByIdAndPassword(member.getId(), member.getPassword());

        log.info("조회된{}", selectMember.toString());

        assertThat(selectMember.getId()).isEqualTo(member.getId());
        assertThat(selectMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(selectMember.getNickname()).isEqualTo(member.getNickname());
    }
}
