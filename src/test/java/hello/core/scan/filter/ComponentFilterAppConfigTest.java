package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        assertThat(ac.getBean("beanA")).isInstanceOf(BeanA.class);
        assertThat(ac.getBean("beanA")).isNotNull();
        Assertions.assertThatThrownBy(() -> ac.getBean("beanB"), "NoSuchBeanDefinitionException");
    }


    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes=MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes=MyExcludeComponent.class)
    ) //이제 컴포넌트 스캔대상, 비대상 설정함 -> @MyIncludeComponent 붙은 것만 읽는다~
    static class ComponentFilterAppConfig {

    }
}
