package com.mmhtoo.note.service;

import com.mmhtoo.note.dto.request.NoteCreateRequestDTO;
import com.mmhtoo.note.entity.Note;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface INoteService {

    Note createNewNote(NoteCreateRequestDTO noteCreateRequestDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException, DuplicateEntityException;

    Note getNoteById(int noteId);

    List<Note> getNoteByNameAndDirId(String name, String dirId);

    List<Note> getNoteByDirId(String dirId);

    boolean isHaveNoteWithSameNameUnderDirectory(String name,String dirId);

    Note getNoteInfo(int noteId) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException;

}
