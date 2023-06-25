package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class MemberServiceTest {

     MemberService memberService;
     OrderService orderService;

     @BeforeEach
     public void befoeEach() {
         AppConfig appConfig = new AppConfig();
         memberService = appConfig.memberService();
         orderService = appConfig.orderService();
     }

    @Test
    void join(){
        //given
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        Member memberB = new Member(2L, "memberB", Grade.BASIC);

        //when
        this.memberService.join(memberA);
        this.memberService.join(memberB);

        Member findMemberB = this.memberService.findMember(memberB.getId());

        //then
        Assertions.assertThat(memberB).isEqualTo(findMemberB);

    }
}
