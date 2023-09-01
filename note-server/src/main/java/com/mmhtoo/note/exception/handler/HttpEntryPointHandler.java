package com.mmhtoo.note.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class HttpEntryPointHandler extends Http403ForbiddenEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        Map<String,String> error = new HashMap<>();
        error.put("error","Unable to access!");
        ResponseEntity<AppResponse> resObj = ResponseUtil.errorResponse(
                HttpStatus.FORBIDDEN ,
                "Unable to access!",
                error
        );
        response.setContentType("application/json");
        response.getWriter().write(
                this.objectMapper.writeValueAsString(resObj)
        );
    }
}
