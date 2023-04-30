package spring.board.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.board.board.Board;
import spring.board.member.Member;

@Getter
@Builder
@Entity(name = "comment")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int commentseq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardseq")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberseq")
    @JsonIgnore
    private Member member;

    @Column
    private String contents;

    @Column
    private String indate;

}
