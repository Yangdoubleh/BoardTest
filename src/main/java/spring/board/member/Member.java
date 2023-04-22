package spring.board.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import spring.board.board.Board;

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

    @Override
    public String toString() {
        return "멤버 객체 확인{" +
                "멤버시퀀스='" + memberseq + '\'' +
                "멤버아이디='" + id + '\'' +
                ", 패스워드='" + password + '\'' +
                ", 닉네임='" + nickname + '\'' +
                '}';
    }

}
