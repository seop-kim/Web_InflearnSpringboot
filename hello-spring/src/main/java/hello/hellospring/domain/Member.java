package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 값을 생성해서 넣는 것이 IDENTITY 이다.
    private Long id;

    private String name;
}
