package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    //부모타입으로 조히하면 자식 타입의 모든 빈들이 함께 조회된다

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있으면 중복 오류가 발생")
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(DiscountPolicy.class);
        });
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름으로 조회하면 된다!")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscoutPolicy = ac.getBean("rateDiscoutPolicy", DiscountPolicy.class);
        assertThat(rateDiscoutPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    //이름없이 특정 타입으로 조회도 가능 (하지만! 구현체에 의존하는 로직이기때문에 비권장)
    @Test
    @DisplayName("특정 하위 타입으로 조회 ")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeansParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for(String key : beansOfType.keySet()) {
            //구현체 이름
//            Object bean = ac.getBean(key);
            System.out.println("구현체 명 : " + key + " , 구현체(bean) : " + ac.getBean(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()) {
           System.out.println("구현체 명 : " + key + " , 구현체(bean) : " + ac.getBean(key));
        }
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscoutPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscoutPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
