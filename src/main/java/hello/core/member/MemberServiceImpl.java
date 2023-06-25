package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //구현체에 의존한다는 문제점이 존재함 ( 역할과 구현 둘 다 의존 -> DIP 위반)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //이제 클라이언트(객체)가 다른 객체(역할)을 부여하는게 아니라 AppConfig가 함
    private final MemberRepository memberRepository; // 역할에만 의존

    public MemberServiceImpl(MemberRepository memberRepository) { //AppConfig에서 memberRepository 구현체를 주입해줄거야
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);

    }
}
