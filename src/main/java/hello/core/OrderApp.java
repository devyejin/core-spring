package hello.core;

import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        //인터페이스,스프링 컨테이너            //구현체
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // ApplicationContext : 스프링 컨테이너
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        Long memberId2 = 2L;
        Member member2 = new Member(memberId2, "memberB", Grade.BASIC);

        //when
        memberService.join(member);
        memberService.join(member2);
        Order order = orderService.createdOrder(memberId, "itemA", 10000);
        Order order2 = orderService.createdOrder(memberId2, "itemB", 20000);

        //then
//        System.out.println(member);
        System.out.println(order); //1000원 할인이 나오겠지?
        System.out.println(order2.calculatePrice());


    }
}
