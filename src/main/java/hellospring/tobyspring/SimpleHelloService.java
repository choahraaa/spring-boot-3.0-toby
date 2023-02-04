package hellospring.tobyspring;

import org.springframework.stereotype.Component;

@MyComponent //메타어노테이션으로 안에 @Component를 적용될 수 있음
public class SimpleHelloService implements HelloService {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
