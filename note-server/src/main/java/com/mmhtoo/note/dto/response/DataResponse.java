package com.mmhtoo.note.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class DataResponse<T> extends AppResponse {

    private T data;

}
