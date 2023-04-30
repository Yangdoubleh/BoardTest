package spring.board.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.board.comment.entity.Comment;
import spring.board.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "board")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int boardseq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberseq")
    @JsonIgnore
    private Member member;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String indate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "board")
    @JsonIgnore
    List<Comment> coments = new ArrayList<>();
}
