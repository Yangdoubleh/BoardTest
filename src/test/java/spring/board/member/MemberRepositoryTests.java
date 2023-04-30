package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import spring.board.BoardApplicationTests;
import spring.board.member.entity.Member;
import spring.board.member.repository.MemberRepository;

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

    @BeforeEach
    public void before() {
        log.info("class = {}", "memberRepository");
    }

    /**
     * 멤버 등록 테스트
     */
    @Test
    @Rollback(value = true)
    public void insertMember() {
        log.info("method = {}", "insertMember");

        Member member = Member.builder()
                .id("insertid")
                .password("insertpassword")
                .nickname("insertnickname")
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
        log.info("method = {}", "selectMember");

        Member member = Member.builder()
                .id("selectid")
                .password("selectpassword")
                .nickname("selectnickname")
                .build();

        log.info("생성한{}", member.toString());

        memberRepository.save(member);

        Member selectMember = memberRepository.findOneById(member.getId());

        log.info("조회된{}", selectMember.toString());

        assertThat(selectMember.getId()).isEqualTo(member.getId());
        assertThat(selectMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(selectMember.getNickname()).isEqualTo(member.getNickname());
    }
}
