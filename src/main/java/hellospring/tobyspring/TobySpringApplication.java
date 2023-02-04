package hellospring.tobyspring;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringApplication {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class); //bean 등록(bean class)
        applicationContext.registerBean(SimpleHelloService.class); //bean 등록(bean class)
        //simpleHelloService bean을 구현한 것이 있는지 찾아보고 구현한 helloController 생성자로 넣어주는데 어떤 걸 먼저 등록해줘도 됨(순서는 상관없음)
        applicationContext.refresh(); //container 초기화(bean object 생성)

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //요청
                    //인증, 보안, 다국어 처리, 공통기능(frontController안에 들어가는 기능)
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        //url의 시작이 hello임과 동시에 요청 메소드가 get일 때를 처리함
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);// bean object 가져옴
                        String ret= helloController.hello(name); //응답(메소드 호출 결과값을 return)

                        //응답
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret); //응답 콘텐츠(내용)
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*"); //mapping을 하여 hello라는 익명함수에 전달이 됨
            //frontController 가 되기 위해서는 들어오는 요청을 다 받아야 하기 때문에 url mapping을 * 로 설정해야함
        });  //getWebServer : servlet을 만드는 메소드(webserver 생성) (WebServer로 return)
        //servletContextInitializer : 서블릿을 서블릿 컨테이너에 등록하는데 필요한 작업을 하기 위해서 사용됨
        webServer.start(); //tomcat servlet container start
    }

}
