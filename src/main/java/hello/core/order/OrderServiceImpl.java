package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

     private final MemberRepository memberRepository; //final 필드는 바로 할당 or 생성자주입을 통해 할당해야함.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //할인 정책 변경
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //클라이언트(요청) OrderServiceImpl 단에서 변경발생 -> OCP원칙 위반!
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createdOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //OrderServiceImpl는 할인에대해 하나도 모름! 즉, 할인이 벼경되어도 할인은 discountPolicy에서 해결함 => SIP (단일책임원칙)

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종생성된 주문 반환
    }
}
