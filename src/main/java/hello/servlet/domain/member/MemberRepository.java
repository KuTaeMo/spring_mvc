package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤 - spring을 사용하면 spring 자체에서 싱글톤을 보장해주기 때문에 따로 싱글톤으로 만들 필요가 없다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    // private으로 생성자를 만들어서 아무나 생성하지 못하도록 막는다
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        // 새로운 리스트에 만들어서 줌으로서 store에 저장되어있는 값은 변경되지 않도록 한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
