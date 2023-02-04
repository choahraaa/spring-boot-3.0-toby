package hellospring.tobyspring;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name)); //Objects.requireNonNull는 파라미터의 값이 null 인지 체크함
    }
}
