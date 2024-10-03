package com.deepanshu.dairydriveapi.aspects;

import com.deepanshu.dairydriveapi.entities.Activity;
import com.deepanshu.dairydriveapi.payloads.DistributorDto;
import com.deepanshu.dairydriveapi.services.ActivityService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Aspect
@Component
public class ActivityAspect {

    @Autowired
    private ActivityService activityService;

    @Pointcut("execution(* com.deepanshu.dairydriveapi.controllers..*(..))")
    public void controllerMethods() {
        // Pointcut to intercept all controller methods
    }

    @Before("controllerMethods() && args(.., distDto)")
    public void logBefore(JoinPoint joinPoint, DistributorDto distDto) {
        logActivity(joinPoint, distDto, null,"before:"+distDto);
    }

    @AfterReturning(pointcut = "controllerMethods() && args(.., distDto)", returning = "result")
    public void logAfter(JoinPoint joinPoint, DistributorDto distDto, Object result) {
        HttpStatusCode statusCode = null;
        boolean isReturnTypeKnown = false;
        if(result instanceof ResponseEntity<?>){
            statusCode = ((ResponseEntity<?>)result).getStatusCode();
            isReturnTypeKnown = true;
        }
        logActivity(joinPoint, distDto, statusCode,(isReturnTypeKnown?"":"Unknown Type:") + result.getClass().getSimpleName()); // Log after success
    }

    @AfterThrowing(pointcut = "controllerMethods() && args(.., distDto)", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, DistributorDto distDto, Throwable error) {
        logActivity(joinPoint, distDto, HttpStatus.OK,error != null?error.getMessage():"No errror msg available:");
    }

    private void logActivity(JoinPoint joinPoint, DistributorDto distDto, HttpStatusCode statusCode, String otherDetails) {
        String userId = distDto.getId();
        String action = joinPoint.getSignature().getName();
        LocalDateTime timestamp = LocalDateTime.now();
        String details = Optional.of(otherDetails).orElse("");
        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setAction(action);
        activity.setTimestamp(timestamp);
        activity.setHttpStatusCode(statusCode);
        activity.setDetails(details.trim());

        activityService.logActivity(activity);
    }
}

