package spring.board.member.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.board.member.entity.Member;
import spring.board.member.repository.MemberRepository;
import spring.board.member.request.MemberRequest;
import spring.board.utils.SHA256;

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
    public Member insertMember(MemberRequest memberRequest) throws Exception {
        Member member = buildMember(memberRequest);
        return memberRepository.save(member);
    }

    /**
     * member id로 유저 찾기
     * @param memberRequest
     * @return
     */
    public Member selectOneMemberById(MemberRequest memberRequest) throws Exception {
        Member member = buildMember(memberRequest);
        return memberRepository.findOneById(member.getId());
    }

    /**
     * 멤버 객체 생성
     * @param memberRequest
     * @return
     */

    private Member buildMember(MemberRequest memberRequest) throws Exception {
        SHA256 sha256 = new SHA256();
        return Member.builder()
                .id(memberRequest.getId())
                .password(sha256.encrypt(memberRequest.getPassword()))
                .nickname(memberRequest.getNickname())
                .build();
    }

    /**
     * 멤버시퀀스로 멤버 찾기
     * @param memberseq
     * @return
     * @throws Exception
     */
    public Member selectOneMemberBySeq(int memberseq) throws Exception {
        return memberRepository.findOneByMemberseq(memberseq);
    }
}
