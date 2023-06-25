package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        
        //이제 객체는 AppConfig에서 데려옴!
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //ApplicationContext이 스프링 컨테이너라고 생각하면 된다!!!
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);//찾아올 (빈 이름, 타입) : 빈 이름은 메서드명


        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);

        System.out.println(memberA.getName());
        System.out.println(findMember.getName());


//-------------------------------------------------------------------------
//        MemberServiceImpl memberService = new MemberServiceImpl();
//        Member memberA = new Member(1L, "memberA", Grade.VIP);
//
//        memberService.join(memberA);
//        Member findMember = memberService.findMember(1L);
//
//        System.out.println(memberA.getName());
//        System.out.println(findMember.getName());
    }
}
