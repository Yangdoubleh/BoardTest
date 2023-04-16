package spring.board.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.member.Member;

/**
 * 멤버 Repository
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * 아이디로 유저 찾기
     * @param id
     * @return
     */
    Member findOneById(String id);

    Member findOneByMemberseq(int seq);
}
