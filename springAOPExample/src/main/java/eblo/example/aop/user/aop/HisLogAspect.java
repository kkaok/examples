package eblo.example.aop.user.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import eblo.example.aop.annotation.HisLogManager;
import eblo.example.aop.annotation.HisLogParam;
import eblo.example.log.service.HisLogService;
import eblo.example.util.IPUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class HisLogAspect {

    @Autowired
    private HisLogService hisLogService;
    
    @AfterReturning(pointcut = "@annotation(eblo.example.aop.annotation.HisLogManager)")
    public void afterReturning(JoinPoint joinPoint) {
        log.debug("afterReturning #####################");
        handleHisLogManger(joinPoint);
    }

    @AfterThrowing(pointcut = "@annotation(eblo.example.aop.annotation.HisLogManager)")
    public void afterThrowing(JoinPoint joinPoint) {
        log.debug("afterThrowing #####################");
        handleHisLogManger(joinPoint);
    }
    
    public void handleHisLogManger(JoinPoint joinPoint) {
        // HisLogManger annotation 설정 값 읽기 
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HisLogManager hisLogManger = method.getAnnotation(HisLogManager.class);

        // HisLogParam 어노테이션이 붙은 파라미터 값 가져오기  
        Object pObj = getAnnotationParam(joinPoint, method);
        //if(pObj != null) System.out.println(pObj.toString());

        // request attribute에서 값 가져오기 
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        hisLogService.save(IPUtils.getRequestIPAddress(request), hisLogManger.appType(), hisLogManger.targetId(), hisLogManger.reqType(), pObj);
    }
    
    private Object getAnnotationParam(JoinPoint joinPoint, Method method) {
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        int i = 0;
        for(Parameter param : parameters) {
            if (param.getAnnotation(HisLogParam.class) != null) {
                return args[i];
            }
            i++;
        }
        return null;
    }
    
    //@Around("@annotation(eblo.example.aop.annotation.HisLogManger) ")
    public Object aroundProcess(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed(pjp.getArgs());
        } finally {
            handleHisLogManger(pjp);
            log.debug("aroundProcess test");
        }
    }


}