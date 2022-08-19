package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되지 않음, 실무에서는 ConcurrentHashMap
 */
public class MemberRepository {
    private static final MemberRepository instance = new MemberRepository();
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }


    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long findId) {
        return store.get(findId);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}