package spring.board.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
