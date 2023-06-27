package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }


    static class TestBean {

        // 자동주입하라는데, Member 객체는 스프링 컨테이너에서 관리하는 빈이 아니기때문에 null
//        @Autowired // required 기본값이 true이기 때문에 주입되는값이 null이면 에러남
//        public void setNoBean(Member noBean) {
//            System.out.println("noBean1 : " + noBean);
//        }


        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 : " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 : " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 : " + noBean3);
            if(!noBean3.isEmpty()) {
                noBean3.get();
            } else {
                System.out.println("null이야!");
            }
        }
    }

}
