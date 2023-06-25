package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류 발생")
    void findBeanByTypeDuplicate(){
//       ac.getBean(MemberRepository.class);
       //NoUniqueBeanDefinitionException:
        // expected single matching bean but found 2: memberRepository1,memberRepository2

        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(MemberRepository.class);
        });

    }


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, '빈 이름'을 지정하면 된다!!")
    void findBeanByName() {
        Object findBean = ac.getBean("memberRepository1",MemberRepository.class); //이름이니까 더블쿼테이션
        org.assertj.core.api.Assertions.assertThat(findBean).isInstanceOf(MemberRepository.class);

    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()) {

            System.out.println("key : " + key + ", bean : " + ac.getBean(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }


    //중복된 타입 만들려면 AppConfig수정해야해서 그냥 static으로 설정파일 만들기
    @Configuration
    static class SameBeanConfig { //inner class-> scope를 outer class만 사용하는거로 잡아버림

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
