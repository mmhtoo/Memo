package com.mmhtoo.note.util;

import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.dto.response.DataResponse;
import com.mmhtoo.note.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

public final class ResponseUtil {

    public static ResponseEntity<AppResponse> errorResponse(
            HttpStatus status ,
            String description ,
            Map<String,String> errors
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        errorResponse.setDescription(description);
        errorResponse.setStatus(status);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatusCode(status.value());

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

    public static ResponseEntity<AppResponse> dataResponse(
            HttpStatus status ,
            String description ,
            Object data
    ){
        DataResponse<Object> response = new DataResponse<>();
        response.setData(data);
        response.setDescription(description);
        response.setStatus(status);
        response.setStatusCode(status.value());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(status)
                .body(response);
    }

    public static ResponseEntity<AppResponse> dataResponse(
            HttpStatus status ,
            String description ,
            Object data ,
            HttpHeaders httpHeaders
    ){
        DataResponse<Object> response = new DataResponse<>();
        response.setData(data);
        response.setDescription(description);
        response.setStatus(status);
        response.setStatusCode(status.value());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(
                response ,
                httpHeaders ,
                status
        );
    }


}
