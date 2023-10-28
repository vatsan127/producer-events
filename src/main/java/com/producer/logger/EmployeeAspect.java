package com.producer.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class EmployeeAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    //controller
    @Around("execution(* com.producer.controller.*.*(..))")
    public Object controllerAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info(proceedingJoinPoint + " Started");

        long startTime = System.currentTimeMillis();

        Object returnValue = proceedingJoinPoint.proceed();

        long stopTime = System.currentTimeMillis();

        long executionTime = (stopTime - startTime);

        logger.info(proceedingJoinPoint + " Executed in {}ms ", executionTime);

        return returnValue;

    }

    @Before("execution(* com.producer.kafka.producer.*.*(..))")
    public void beforeKafkaProducer(JoinPoint joinPoint) {
        logger.info("Method Called - {}", joinPoint);
    }

    @AfterReturning("execution(* com.producer.kafka.producer.*.*(..))")
    public void afterKafkaProducer(JoinPoint joinPoint) {
        logger.info("Kafka Message Sent - {} method execution Successful", joinPoint);
    }

    @Before("execution(* com.producer.database.*.*(..))")
    public void beforeEmployeeRepositry(JoinPoint joinPoint) {
        logger.info("Method Called - {}", joinPoint);
    }

    @AfterReturning("execution(* com.producer.database.EmployeeRepositry.insert(..))")
    public void afterEmployeeRepositryInsert(JoinPoint joinPoint) {
        logger.info("Employee added to database - {} ", joinPoint);
    }

    @AfterReturning("execution(* com.producer.database.EmployeeRepositry.delete(..))")
    public void afterEmployeeRepositryDelete(JoinPoint joinPoint) {
        logger.info("Employee removed database - {} ", joinPoint);
    }

    @AfterReturning("execution(* com.producer.database.EmployeeRepositry.*(..))")
    public void afterEmployeeRepositry(JoinPoint joinPoint) {
        logger.info("{} method execution Successful ", joinPoint);
    }


    @Before("execution(* com.producer.database.EmployeeService.*(..))")
    public void beforeEmployeeService(JoinPoint joinPoint) {
        logger.info("Method Called - {}", joinPoint);
    }

    @AfterReturning("execution(* com.producer.database.EmployeeService.*(..))")
    public void afterEmployeeService(JoinPoint joinPoint) {
        logger.info("{} method execution Successful", joinPoint);
    }


}
