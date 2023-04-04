package spring.board.member;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name="member")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int memberseq;
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;

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
