package com.mmhtoo.note.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DirectoryCreateReqDTO {

    @NotNull( message = "Directory's Name is required!" )
    @NotEmpty( message = "Directory's Name is required!" )
    @Length( min = 1 , max = 30 , message = "Directory must be between 1 to 30 characters!")
    private String name;

    private String description;

    private String accountId;

    private String parentDirId;

}
