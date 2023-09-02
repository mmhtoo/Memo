package com.mmhtoo.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DirectoryResponseDTO {

    private String directoryId;

    private String name;

    private String description;

    private int numberOfNotes;

    private String parentDirId;

    private String parentDirName;

    @JsonFormat( pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat( pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updatedDate;

}
