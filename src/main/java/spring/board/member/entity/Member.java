package spring.board.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.board.board.entity.Board;
import spring.board.comment.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name="member")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int memberseq;
    @Column()
    private String id;
    @Column()
    private String password;
    @Column()
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    @JsonIgnore
    List<Board> boards = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    @JsonIgnore
    List<Comment> coments = new ArrayList<>();
}
