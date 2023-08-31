package com.mmhtoo.note.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DirectoryCreateReqDTO {

    @NotNull( message = "Name is required!" )
    @NotEmpty( message = "Name is required!" )
    @Length( min = 1 , max = 30 , message = "Directory must be between 1 to 30 characters!")
    private String name;

    @NotNull( message = "Description is required!" )
    @NotEmpty( message = "Description is required!" )
    @Length( max = 50 , message = "Maximum allowed characters for description is 50!")
    private String description;

    @NotNull( message = "Account Id is required!")
    @NotEmpty( message = "Account Id is required!" )
    private String accountId;

    private String parentDirId;

}
