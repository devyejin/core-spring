package hello.core.singleton;


import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig(); //ApplicationContext 를 사용하면 스프리 컨테이너에 빈을 등록하고 사용하게되는거니까, 싱글턴으로 관리됨, 그래서 순수 new AppConfig로 생성!

        //1. 조회
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 ( 조회할 때 마다 새로운 객체 생성)
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다름!
//        System.out.println(memberService1); //MemberServiceImpl@3857f613
//        System.out.println(memberService2); //MemberServiceImpl@198b6731
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
