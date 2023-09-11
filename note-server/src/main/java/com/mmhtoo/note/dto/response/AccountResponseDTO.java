package com.mmhtoo.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccountResponseDTO {

    private String accountId;

    private String username;

    private String email;

    private String password;

    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime joinedDate;

    @JsonFormat( pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedDate;

    private boolean isLocked;

}
