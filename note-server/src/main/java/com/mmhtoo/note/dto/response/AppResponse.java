package com.mmhtoo.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class AppResponse {

    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime timestamp;

    protected HttpStatus status;

    protected Integer statusCode;

    protected String description;

}
