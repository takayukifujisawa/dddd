package omu.dddd;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* omu.dddd.presentation.*Controller.*(..))")
    public Object controllerExecutionLog(ProceedingJoinPoint jp) throws Throwable {

        logger.debug("**before**：" + Arrays.toString(jp.getArgs())
                + ", " + jp.getTarget().getClass() + "#"
                + jp.getSignature().getName());

        try {
            Object result = jp.proceed();

            logger.info("**exexute**：" + Arrays.toString(jp.getArgs())
                    + ", " + jp.getTarget().getClass() + "#"
                    + jp.getSignature().getName());
            return result;

        } catch (Exception e) {
            logger.error("**ERROR**", e);
            throw e;
        }
    }   
}