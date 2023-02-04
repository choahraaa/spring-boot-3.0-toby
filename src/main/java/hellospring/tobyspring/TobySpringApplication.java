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
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringApplication {

    public static void main(String[] args) {
        //springcontainer 생성
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class); //bean 등록(bean class)
        applicationContext.registerBean(SimpleHelloService.class); //bean 등록(bean class)
        //simpleHelloService bean을 구현한 것이 있는지 찾아보고 구현한 helloController 생성자로 넣어주는데 어떤 걸 먼저 등록해줘도 됨(순서는 상관없음)
        applicationContext.refresh(); //container 초기화(bean object 생성)

        //servletContainer를 코드로 실행하면서 servlet을 등록
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcher",
                    new DispatcherServlet(applicationContext) //dispatcherServlet은 wedapplicationcontext type을 사용해야함
            ).addMapping("/*");
        });
        webServer.start(); //tomcat servlet container start
    }

}
