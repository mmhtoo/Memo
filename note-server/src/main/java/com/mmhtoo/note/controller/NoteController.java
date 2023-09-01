package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.NoteCreateRequestDTO;
import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.entity.Note;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.mapper.NoteMapper;
import com.mmhtoo.note.service.INoteService;
import com.mmhtoo.note.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping( value = "${api.notes.root}" )
@AllArgsConstructor
public class NoteController extends BaseController {

    private final INoteService noteService;

    @CheckBinding
    @PostMapping
    public ResponseEntity<AppResponse> createNote(
            @Valid @RequestBody NoteCreateRequestDTO noteCreateRequestDTO ,
            BindingResult bindingResult
    ) throws InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, DuplicateEntityException {
        Note createdNote = this.noteService.createNewNote(noteCreateRequestDTO);
        return ResponseUtil.dataResponse(
                HttpStatus.CREATED ,
                "Successfully created note!",
                NoteMapper.noteToNoteResponseDto(createdNote)
        );
    }

    @GetMapping
    public ResponseEntity<AppResponse> getNoteByDirectoryId(
            @RequestParam( value = "directory" ) String directoryId
    ){
        List<Note> notes = this.noteService.getNoteByDirId(directoryId);
        return ResponseUtil.dataResponse(
                HttpStatus.OK ,
                "Success",
                notes.stream().map(NoteMapper::noteToNoteResponseDto)
        );
    }

    @GetMapping( value = "${api.notes.targetDir}" )
    public ResponseEntity<AppResponse> getNoteInfo(
            @PathVariable( value = "noteId") Integer noteId
    ) throws InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Note note = this.noteService.getNoteInfo(noteId);
        return ResponseUtil.dataResponse(
                HttpStatus.OK ,
                "Success!",
                note == null
                ? null : NoteMapper.noteToNoteResponseDto(
                        this.noteService.getNoteInfo(noteId)
                )
        );
    }

}
