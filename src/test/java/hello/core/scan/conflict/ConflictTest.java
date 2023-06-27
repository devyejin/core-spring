package hello.core.scan.conflict;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConflictTest {

    @Test
    @DisplayName("충돌 테스트")
    void conflictTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        Object service = ac.getBean("service"); //서비스란 이름의 빈을 데려와!

    }

    @Test
    @DisplayName("수동 자동 충돌 테스트")
    void autoSelfConflict() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        System.out.println(ac.getBean("service"));
        Assertions.assertThat(ac.getBean("service")).isInstanceOf(MemberRepository.class);

    }
}
