package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);//스프링 컨테이너에 스프링 빈 등록하기

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        //단순히 얼마를 할인받는지 조회하는 서비스라 가정
        int discountAmount = discountService.discount(member, 10000, "fixDiscoutPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountAmount).isEqualTo(1000);

        int discount = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discount).isEqualTo(2000);

    }

    static class DiscountService {
        //요로콤 Map과 List를 이용해서 필드를 선언해놓으면 타입에 해당하는 모든 객체를 주입해준다!!
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;


         //생성자가 하나뿐이라 생략 가능
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap : " + policyMap); //찍어보니 진짜 주입됨!
            System.out.println("policies : " + policies );
        }

        public int discount(Member member, int price, String discountCode) { //코드랑 빈 이름을 매칭시킴
            //코드를 넣으면 할인 정책이 적용되서 나온다
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }

}
