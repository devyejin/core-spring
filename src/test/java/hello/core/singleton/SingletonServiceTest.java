package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonServiceTest {
    public static void main(String[] args) {

        //'SingletonService()' has private access in 'hello.core.singleton.SingletonService'
//        SingletonService singletonService = new SingletonService();

        //get으로 얻어 와야 함!
        SingletonService instance = SingletonService.getInstance();
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
//        new SingletonService(); // 컴파일 오류!
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2); //isSameAs() 메서드는 참조값 비교, 즉 동일객체인지!

    }

    @Test
    @DisplayName("isSameAs()와 isEqualTo() 메서드의 차이 알아보기")
    void difference() {
        String str1 = "Hello";
        String str2 = new String("Hello");
// str1, str2 서로 다른 객체지만 내용이 동일하므로 isEqualTo() 하면 true

        assertThat(str1).isEqualTo(str2); // true -> 객체는 다르지만 내용이 동일하니까 true (내용비교)
        assertThat(str1).isNotSameAs(str2); // false -> 객체비교! 내용은 동일하지만 참조값이 다르므로 false
    }


    @Test
    @DisplayName("스프링 컨테이너 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //같은 객체인지 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2); //isEqualTo대신 isSameAs 사용해서 참조값 비교!
    }

    /**
     * 호출되는거 예상
     * call AppConig : memberService!!
     * call AppConig : memberRepository!!
     * call AppConig : orderService!!
     * call AppConig : memberRepository <----여기부터 호출이 안됨(실제로)
     * call AppConig : memberRepository
     *
     *
     * 실제 호출된 결과
     * call AppConig : memberService!!
     * call AppConig : memberRepository!!
     * call AppConig : orderService!!
     *
     * 즉,
     */

    @Test
    @DisplayName("서로 다른 Service에서 동일한 Repository를 사용해도 싱글턴 유지되는지 테스트")
    void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

//        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
//        Assertions.assertThat(memberRepository).isSameAs(memberService.getMemberRepository());
//        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());

        System.out.println(memberService.getClass());
        System.out.println(memberRepository.getClass());
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig 또한 스프링컨테이너에 스프링 빈으로 등록이 됨 (@Configuration 안에 @Bean 있음)
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean : " + bean.getClass()); //bean : class hello.core.AppConfig$$EnhancerBySpringCGLIB$$e01199a3
    }



}
