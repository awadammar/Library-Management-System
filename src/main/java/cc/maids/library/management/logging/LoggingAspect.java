package cc.maids.library.management.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* cc.maids.library.management.service.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String params = Arrays.toString(joinPoint.getArgs());

        logger.log(Level.INFO, "Method " + methodName + " of class " + className + " called with parameters: " + params);
    }

    @AfterThrowing(pointcut = "execution(* cc.maids.library.management.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.log(Level.SEVERE, "Exception thrown in method " + methodName + " of class " + className + ": " + ex.getMessage(), ex);
    }
}
