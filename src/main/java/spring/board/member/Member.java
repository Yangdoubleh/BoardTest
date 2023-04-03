package spring.board.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(nullable = false, unique = true)
    private String Id;
    @Column(nullable = false)
    private String Password;
    @Column(nullable = false)
    private String Nickname;

    @Override
    public String toString() {
        return "멤버 객체 확인{" +
                "멤버아이디='" + Id + '\'' +
                ", 패스워드='" + Password + '\'' +
                ", 닉네임='" + Nickname + '\'' +
                '}';
    }
}
