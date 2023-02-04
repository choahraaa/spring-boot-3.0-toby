package hellospring.tobyspring;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping ("/hello") //class level로 들어온 mapping으로 요청을 먼저 구분
@Component
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping  //dispatcherServlet에서 mapping 하는 설정을 간략화
    @ResponseBody //응답을 text-plain으로 전달하기 위해 사용한 어노테이션
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name)); //Objects.requireNonNull는 파라미터의 값이 null 인지 체크함
    }
}
