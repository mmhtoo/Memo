package com.mmhtoo.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteResponseDTO {

    private int noteId;

    private String name;

    private String title;

    private String content;

    @JsonFormat( pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat( pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updatedDate;

}
