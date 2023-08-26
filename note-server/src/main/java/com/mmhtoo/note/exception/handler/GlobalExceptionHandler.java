package com.mmhtoo.note.exception.handler;

import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
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

    // for requested path not found exception
    @ExceptionHandler({ NoHandlerFoundException.class })
    public ResponseEntity<AppResponse> notFoundException(Exception e ){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());
        return ResponseUtil.errorResponse(
                HttpStatus.NOT_FOUND ,
                e.getMessage() ,
                error
        );
    }

    // for data duplication and need verification request
    @ExceptionHandler({ DuplicateEntityException.class , NeedVerificationException.class })
    public ResponseEntity<AppResponse> duplicateAndNeedVerificationException(Exception e ){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());
        return ResponseUtil.errorResponse(
                HttpStatus.BAD_REQUEST ,
                e.getMessage() ,
                error
        );
    }

    // for invalid data accessing
    @ExceptionHandler({ InvalidDataAccessException.class })
    public ResponseEntity<AppResponse> invalidDataAccessException(Exception e ){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());
        return ResponseUtil.errorResponse(
                HttpStatus.NOT_ACCEPTABLE ,
                e.getMessage() ,
                error
        );
    }

}
