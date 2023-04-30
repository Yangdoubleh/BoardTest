package spring.board.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
