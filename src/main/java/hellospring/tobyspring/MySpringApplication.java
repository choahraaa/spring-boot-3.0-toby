package hellospring.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args ) {
        //springcontainer 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            //확장하고 싶은 기능들
            @Override
            protected void onRefresh() { //dispatcherServlet 초기화하는 중에 작업해야 할 것들이 있을 때 사용
                super.onRefresh();

                //servletContainer를 코드로 실행하면서 servlet을 등록
                TomcatServletWebServerFactory serverFactory = this.getBean(TomcatServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcher", dispatcherServlet).addMapping("/*");
                });
                webServer.start(); //tomcat servlet container start
            }
        };
        applicationContext.register(applicationClass);
        //simpleHelloService bean을 구현한 것이 있는지 찾아보고 구현한 helloController 생성자로 넣어주는데 어떤 걸 먼저 등록해줘도 됨(순서는 상관없음)
        applicationContext.refresh(); //container 초기화(bean object 생성)
    }
}
