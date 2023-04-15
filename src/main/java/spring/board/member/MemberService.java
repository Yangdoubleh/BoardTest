package spring.board.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.board.request.MemberRequest;

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
     * @param memberRequest
     * @return
     */
    public Member insertMember(MemberRequest memberRequest) {
        Member member = buildMember(memberRequest);
        return memberRepository.save(member);
    }

    /**
     * member id로 유저 찾기
     * @param memberRequest
     * @return
     */
    public Member selectOneMemberById(MemberRequest memberRequest) {
        Member member = buildMember(memberRequest);
        return memberRepository.findOneById(member.getId());
    }

    /**
     * 멤버 객체 생성
     * @param memberRequest
     * @return
     */

    private Member buildMember(MemberRequest memberRequest) {
        return Member.builder()
                .id(memberRequest.getId())
                .password(memberRequest.getPassword())
                .nickname(memberRequest.getNickname())
                .build();
    }
}
