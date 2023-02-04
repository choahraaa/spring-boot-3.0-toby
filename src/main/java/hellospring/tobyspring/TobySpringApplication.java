package hellospring.tobyspring;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ComponentScan //component annotation이 붙은 class를 bean으로 등록해달라는 annotation
public class TobySpringApplication {

    public static void main(String[] args) {
        //springcontainer 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
          //확장하고 싶은 기능들
            @Override
            protected void onRefresh() { //dispatcherServlet 초기화하는 중에 작업해야 할 것들이 있을 때 사용
                super.onRefresh();

                //servletContainer를 코드로 실행하면서 servlet을 등록
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcher",
                            new DispatcherServlet(this) //applicationContext 내에서 실행하는 것으로 자기 자신을 호출하면
                            //dispatcherServlet은 wedapplicationcontext type을 사용해야함
                    ).addMapping("/*");
                });
                webServer.start(); //tomcat servlet container start
            }
        };
        applicationContext.register(TobySpringApplication.class);
        //simpleHelloService bean을 구현한 것이 있는지 찾아보고 구현한 helloController 생성자로 넣어주는데 어떤 걸 먼저 등록해줘도 됨(순서는 상관없음)
        applicationContext.refresh(); //container 초기화(bean object 생성)
    }

}
