package eblo.example.aop.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eblo.example.aop.log.domain.AppType;
import eblo.example.aop.log.domain.ReqType;
import eblo.example.aop.log.domain.TargetId;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HisLogManager {
    
    AppType appType() default AppType.WEB;
    TargetId targetId();
    ReqType reqType() default ReqType.READ;

}
