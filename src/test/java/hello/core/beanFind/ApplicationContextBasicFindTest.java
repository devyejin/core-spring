package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService); //hello.core.member.MemberServiceImpl@5e1fa5b1 (인스턴스를 출력 )
//        System.out.println("memberService.getClass() = " + memberService.getClass()); // class hello.core.member.MemberServiceImp (클래스타입을 출력)

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
        //타입으로만 조회시, 같은 타입이 여러개 존재할 때 문제임
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
//        System.out.println("memberService = " + memberService); //hello.core.member.MemberServiceImpl@5e1fa5b1 (인스턴스를 출력 )
//        System.out.println("memberService.getClass() = " + memberService.getClass()); // class hello.core.member.MemberServiceImp (클래스타입을 출력)

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("구체 타입으로 조회")
        //구현체 타입으로 조회(인터페이스타입 조회는 구현체가 여러개인경우 문제가 되니까)
    void findBeanByType2() {
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService); //hello.core.member.MemberServiceImpl@5e1fa5b1 (인스턴스를 출력 )
//        System.out.println("memberService.getClass() = " + memberService.getClass()); // class hello.core.member.MemberServiceImp (클래스타입을 출력)

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    //AppConfig에는 MemberService 인터페이스로 등록했는데 어떻게 구현체 타입으로 Bean을 조회할 수 있을까?
    //등록될 때 구현체타입을 보고 등록되기 때문!
    //근데 이렇게 구현체타입으로 하는건 좋지 않은 코드!!! 항상 역할과 구현을 구분!역할에 의존해라!!!!
    //지금 로직은 구현에 의존하기때문에 좋지 않은코드이지만.. 가끔 필요할때 사용해라.

    //실패테스트
    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameFail() {
        //ac.getBean("xxxx",MemberService.class);가 없다면?!
//        MemberService xxx = ac.getBean("xxx", MemberService.class); //NoSuchBeanDefinitionException
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxx", MemberService.class));


    }


}
