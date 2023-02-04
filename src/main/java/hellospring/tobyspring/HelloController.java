package hellospring.tobyspring;

import java.util.Objects;

public class HelloController {
    public String hello(String name){
        //controller에 주요기능은 유저의 요청사항을 확인해야함
        SimpleHelloService helloService = new SimpleHelloService(); //service를 사용하여 인사말을 하는 로직을 분리

        return helloService.sayHello(Objects.requireNonNull(name)); //Objects.requireNonNull는 파라미터의 값이 null 인지 체크함
    }
}
