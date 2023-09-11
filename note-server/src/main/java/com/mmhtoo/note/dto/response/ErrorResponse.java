package com.mmhtoo.note.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public final class ErrorResponse extends AppResponse {

    private Map<String,String> errors;

}
