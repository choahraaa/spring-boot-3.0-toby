package hellospring.tobyspring;


import org.springframework.boot.SpringApplication;

@MySpringBootApplication//component annotation이 붙은 class를 bean으로 등록해달라는 annotation
public class TobySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TobySpringApplication.class, args);
    }

}
