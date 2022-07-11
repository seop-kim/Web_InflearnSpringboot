package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원 저장 테스트")
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        // 객체 반환 변수 자동 생성 단축키 : [option + command + v]
        // Optional 에서 값을 꺼내오는 메소드는 get 이지만 잘 사용하지 않는다.
        Member result = memberRepository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result); // 1번째 인자 : 기대할 값, 2번째 인자 : 확인할 값

        //import org.assertj.core.api.Assertions; 클래스의 메소드이다. 이는 좀 더 편하게 사용 가능한 기능을 제공한다.
        assertThat(member).isEqualTo(result); // Assertions 를 스태틱 메소드로 변경한다 [option + enter]
    }

    @Test
    @DisplayName("이름으로 회원 찾기")
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        // [shift + F6] 을 변수에서 누르면 해당 변수를 사용하는 값들을 한번에 수정할 수 있게 해준다.
        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    @DisplayName("멤버 전체 조회")
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}