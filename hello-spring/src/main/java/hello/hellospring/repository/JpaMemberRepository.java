package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id); // 조회할 타입과 검색 값을 매개변수로 넘겨준다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> resultList =
                entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                        .setParameter("name", name)
                        .getResultList();

        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
