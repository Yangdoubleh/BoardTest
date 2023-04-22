package spring.board.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.board.member.Member;

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

    @Override
    public String toString() {
        return "게시판 객체 확인{" +
                "게시판시퀀스=" + boardseq +
                ", 작성자정보=" + member +
                ", 타이틀='" + title + '\'' +
                ", 콘텐츠='" + contents + '\'' +
                ", 등록일='" + indate + '\'' +
                '}';
    }
}
