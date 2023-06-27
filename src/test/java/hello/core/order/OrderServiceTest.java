package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void befoeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();

    }


//    @Test
    void createOrder() {
        //given
        Long memberId = 1L; //Long 잡은 이유는, primitive type의 경우 null을 못 넣어서
        Member member = new Member(memberId, "memberA", Grade.VIP);


        //when
        memberService.join(member);
        Order order = orderService.createdOrder(memberId, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
