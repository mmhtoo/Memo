package com.mmhtoo.note.mapper;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.dto.response.DirectoryResponseDTO;
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

    public static DirectoryResponseDTO directoryToDirectoryResponseDTO(
            Directory directory
    ){
         return DirectoryResponseDTO.builder()
                 .directoryId(directory.getId())
                 .name(directory.getName())
                 .description(directory.getDescription() == null ? "" : directory.getDescription())
                 .parentDirId(directory.getParentDir() == null ? null : directory.getParentDir().getId())
                 .parentDirName(directory.getParentDir() == null ? null : directory.getParentDir().getName())
                 .numberOfNotes(directory.getNumberOfNotes())
                 .createdDate(directory.getCreatedDate())
                 .updatedDate(directory.getUpdatedDate())
                 .build();
    }

}
