package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class MemoryMemberRepository implements MemberRepository{

    //DB대신 메모리 저장소
    private final static HashMap<Long,Member> store =  new HashMap<>(); //멀티쓰레드 환경에서는 ConcurrentHashMap

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
