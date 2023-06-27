package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {

//    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);

        //정말 제대로 꺼내진게 맞는지 검증
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }


    @Test
    @DisplayName("basePackages 테스트")
    void baseTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        OrderService bean = ac.getBean(OrderService.class);
//        System.out.println(bean); //NoSuchBeanDefinitionException

        Assertions.assertThatThrownBy( () ->{
            ac.getBean(OrderService.class, "NoSuchBeanDefinitionException");
        });
    }
}
