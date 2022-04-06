package hello.hello_spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.ObjectStreamClass;

@Aspect
@Component
public class TimeTraceApp {

    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start: "+joinPoint.toString());
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: "+joinPoint.toString() + " " + timeMs + "ms");
        }

    }

}
