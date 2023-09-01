package com.mmhtoo.note.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NoteCreateRequestDTO {

    @NotNull( message = "Note's name is required!" )
    @NotEmpty( message = "Note's name is required!")
    private String name;

    private String title;

    private String content;

    @NotNull( message = "Directory's id is required!")
    @NotEmpty( message = "Directory's id is required!")
    private String directoryId;

}
