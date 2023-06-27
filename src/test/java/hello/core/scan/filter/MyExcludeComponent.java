package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //이게 중요함 -> 클래스레벨에 붙는 어노테이션이다!
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
