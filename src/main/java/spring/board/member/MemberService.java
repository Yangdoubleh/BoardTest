package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 멤버 Service
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * member 등록
     * @param member
     * @return
     */
    public Member insertMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * member id로 유저 찾기
     * @param member
     * @return
     */
    public Member selectOneMemberById(Member member) {
        return memberRepository.findOneById(member.getId());
    }
}
