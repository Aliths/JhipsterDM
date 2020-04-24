package com.mycompany.myapp.aop.logging;

import io.github.jhipster.config.JHipsterConstants;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.util.Arrays;

/*
 * Aspect for logging execution of service and repository Spring components.
 *
 * By default, it only runs with the "dev" profile.
*/
@Aspect
@Component
public class MailLoggingAspect {

    private final Environment env;

    public MailLoggingAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("execution(public void send(..))")
    public void sendEmail() {}

    @Pointcut("within(com.mycompany.myapp.service.MailService)")
    public void inPackage() {}

    @Around("inPackage() && sendEmail()")
    public void sendNewEmail(JoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger("mailService");
        if (log.isDebugEnabled()) {
            log.debug("EnterMail: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            if (log.isDebugEnabled()) {
                log.debug("ExitMail: {}()", joinPoint.getSignature().getName());
            }
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
