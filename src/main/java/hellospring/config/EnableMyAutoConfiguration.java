package hellospring.config;

import hellospring.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({TomcatWebServerConfig.class, DispatcherServlet.class}) //component 어노테이션을 가지고 있응 때, 클래스 정보를 넘겨주면 구성정보에 직접 추가 가능
public @interface EnableMyAutoConfiguration {
}
