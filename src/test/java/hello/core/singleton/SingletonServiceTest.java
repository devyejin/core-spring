package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}
