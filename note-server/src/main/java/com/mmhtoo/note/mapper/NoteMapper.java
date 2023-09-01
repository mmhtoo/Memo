package com.mmhtoo.note.mapper;

import com.mmhtoo.note.dto.request.NoteCreateRequestDTO;
import com.mmhtoo.note.dto.response.NoteResponseDTO;
import com.mmhtoo.note.entity.Note;

import java.time.LocalDateTime;

public class NoteMapper {

    public static Note noteCreateReqDtoToNote(NoteCreateRequestDTO noteCreateRequestDTO){
        return Note.builder()
                .name(noteCreateRequestDTO.getName())
                .title(noteCreateRequestDTO.getTitle())
                .content(noteCreateRequestDTO.getContent())
                .createdDate(LocalDateTime.now())
                .updatedDate(null)
                .build();
    }

    public static NoteResponseDTO noteToNoteResponseDto(Note note){
        return NoteResponseDTO.builder()
                .noteId(note.getId())
                .name(note.getName())
                .title(note.getTitle())
                .content(note.getContent())
                .createdDate(note.getCreatedDate())
                .updatedDate(note.getUpdatedDate())
                .build();
    }

}
