package com.mmhtoo.note.exception.handler;

import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ NoHandlerFoundException.class })
    public ResponseEntity<AppResponse> notFoundException(NoHandlerFoundException e ){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());
        return ResponseUtil.errorResponse(
                HttpStatus.NOT_FOUND ,
                e.getMessage() ,
                error
        );
    }

}
