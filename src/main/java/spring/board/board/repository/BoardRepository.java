package spring.board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.board.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
