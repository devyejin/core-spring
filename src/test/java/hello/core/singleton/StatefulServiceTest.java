package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱륵턴은 여러 클라이언트가 요청할 때 stateful이므로 읽기전용으로만 사용해야한다.")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
//        StatefulService statefulService = ac.getBean("statefulService", StatefulService.class);
//        System.out.println("초기 값 " + statefulService.getPrice());
//        statefulService.order("싱글턴 접근해서 값 변경", 100);
//        System.out.println("초기 값 " + statefulService.getPrice());
//
//        //새로운 클라이언트가 요청
//        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
//        System.out.println("새로운 객체가 접근" + statefulService2.getPrice());

        //위에는 내가 생각해서 해본 로직이고, 밑에는 수업. 역시 코드가 깔끔!
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 1만원 주문
        statefulService1.order("userA", 10_000);

        //ThreadB : B사용자 2만원 주문
        statefulService2.order("userB",20_000);

        //ThreadA :  사용자A 주문금액 조회
//        Assertions.assertThat(statefulService1.getPrice()).isNotEqualTo(10_000);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20_000);

        //사용자 B가 값을 변경했기 때문에! => 실무에서 이런 문제가 종종 발생한다.

    }

    @Test
    @DisplayName("stateless한 싱글턴 객체")
    void statelessSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        Assertions.assertThat(statefulService1.order("userA",10000)).isEqualTo(10000);
        Assertions.assertThat(statefulService2.order("userB",500)).isEqualTo(500);

    }
        @Configuration
        static class TestConfig {
        @Bean
        StatefulService statefulService(){
            return new StatefulService();
        }
    }

}