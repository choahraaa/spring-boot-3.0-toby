package hellospring.tobyspring;

import org.springframework.stereotype.Component;

@Component //bean objsct 로 사용될 class
public class SimpleHelloService implements HelloService {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
