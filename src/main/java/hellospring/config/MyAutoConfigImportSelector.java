package hellospring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                //문자열로 추가(설정파일 추가) - 문자열을 가지고 파일을 찾음
                "hellospring.config.autoconfig.DispatcherServletConfig",
                "hellospring.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
