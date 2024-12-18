package com.decathlon.domain_name.technical.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Aspect
@Configuration
public class LogAspectConfig {

    @Before("execution(* com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.controllers.*.*(..))")
    public void loggingBefore(JoinPoint joinPoint) {

        log.debug("# Aspect : Calling the method _ {} _ using these parameters {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.controllers.*.*(..))", returning = "result")
    // Executed only when a method executes successfully
    public void loggingAfterSuccess(JoinPoint joinPoint, Object result) {

        log.debug("# Aspect : Result of the method _ {} _ is {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "execution(* com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.controllers.*.*(..))", throwing = "ex")
    // Executed only when a method throws an exception
    public void loggingAfterFailing(JoinPoint joinPoint, Exception ex) {

        log.error("# Aspect : Throw exception after executing the method _ {} _ exception was {}", joinPoint.getSignature(), ex.toString());
    }
}
