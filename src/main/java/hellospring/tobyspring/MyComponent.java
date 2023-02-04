package hellospring.tobyspring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 어디까지 살아있을지 정하는 부분
@Target(ElementType.TYPE) //어노테이션이 어느 타입에 적용되는지 정하는 부분
@Component
public @interface MyComponent {
}
