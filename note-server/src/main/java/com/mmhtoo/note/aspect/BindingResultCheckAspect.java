package com.mmhtoo.note.aspect;

import com.mmhtoo.note.util.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class BindingResultCheckAspect {

    @Around(
            value = "@annotation(com.mmhtoo.note.annotation.CheckBinding) && args(..)"
    )
    public Object bindingCheckAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object [] args = proceedingJoinPoint.getArgs();
        BindingResult bindingResult = (BindingResult) args[1];

        // getting error map from binding result
        Map<String,String> error = new HashMap<>();

        bindingResult.getFieldErrors()
                .forEach( fieldError -> {
                    error.put(fieldError.getField(),fieldError.getDefaultMessage());
                });

        if(bindingResult.hasErrors())
            return ResponseUtil.errorResponse(
                    HttpStatus.BAD_REQUEST ,
                    "Invalid Request!" ,
                    error
            );

        return proceedingJoinPoint.proceed(args);
    }

}
