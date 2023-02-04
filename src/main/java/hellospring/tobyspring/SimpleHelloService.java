package hellospring.tobyspring;

public class SimpleHelloService implements HelloService {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
