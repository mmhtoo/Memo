package com.mmhtoo.note.mapper;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.Directory;

import java.time.LocalDateTime;
import java.util.UUID;

public class DirectoryMapper {

     public static Directory directCreateReqDtoToDirectory(
            DirectoryCreateReqDTO directoryCreateReqDTO
    ){
        return Directory.builder()
                .id(UUID.randomUUID().toString())
                .name(directoryCreateReqDTO.getName())
                .description(directoryCreateReqDTO.getDescription())
                .createdDate(LocalDateTime.now())
                .account(Account.builder().id(directoryCreateReqDTO.getAccountId()).build())
                .numberOfNotes(0)
                .build();
    }

}
