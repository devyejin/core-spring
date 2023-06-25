package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig { //구현 객체를 생성하고, 주입(책임을 부여) 하는 역할 -> 애플리케이션 전반을 관리


    @Bean
    public MemberService memberService() { //MemberService라는 구현체도 AppConfig에서 만드는거지
        return new MemberServiceImpl(memberRepository()); // MemberServiceImpl 구현체가 생성될 때, MemoryMemberRepository 도 생성해서 의존성을 주입해줌 => 생성자 주입
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
