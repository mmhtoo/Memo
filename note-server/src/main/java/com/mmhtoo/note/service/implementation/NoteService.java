package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.interfaces.Claim;
import com.mmhtoo.note.dto.request.NoteCreateRequestDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.entity.Note;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.mapper.NoteMapper;
import com.mmhtoo.note.repository.NoteRepo;
import com.mmhtoo.note.service.IAccountService;
import com.mmhtoo.note.service.IDirectoryService;
import com.mmhtoo.note.service.INoteService;
import com.mmhtoo.note.service.ITokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NoteService implements INoteService  {

    private final NoteRepo noteRepo;
    private final HttpServletRequest request;
    private final ITokenService tokenService;
    private final IAccountService accountService;
    private final IDirectoryService directoryService;

    @Override
    public Note createNewNote(NoteCreateRequestDTO noteCreateRequestDTO)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException, DuplicateEntityException {
        Map<String, Claim> payload = this.tokenService.getPayloadFromRequest(request);

        if( payload == null )
            throw new InvalidDataAccessException("Invalid account for access!");

        if( this.isHaveNoteWithSameNameUnderDirectory(noteCreateRequestDTO.getName(),noteCreateRequestDTO.getDirectoryId()))
            throw new DuplicateEntityException("Note with "+noteCreateRequestDTO.getName()+" name already exists!");

        Account savedAccount = this.accountService
                .getAccountByAccountId(payload.get("userId").asString());
        Directory savedDir = noteCreateRequestDTO.getDirectoryId() == null
                ? null
                : this.directoryService.getDirectoryById(noteCreateRequestDTO.getDirectoryId());

        if( savedAccount == null || (noteCreateRequestDTO.getDirectoryId() != null && savedDir == null ))
            throw new InvalidDataAccessException("Invalid account or directory for creating note!");

        Note newNote = NoteMapper.noteCreateReqDtoToNote(
                noteCreateRequestDTO
        );
        newNote.setAccount(savedAccount);
        newNote.setDirectory(savedDir);

        return this.noteRepo.save(newNote);
    }

    @Override
    public Note getNoteById(int noteId) {
        return this.noteRepo.findById(noteId)
                .orElse(null);
    }

    @Override
    public List<Note> getNoteByNameAndDirId(String name, String dirId) {
        return this.noteRepo.findByNameAndDirectoryId(
                name ,
                dirId
        );
    }

    @Override
    public List<Note> getNoteByDirId(String dirId) {
        return this.noteRepo.findByDirectoryId(dirId);
    }

    @Override
    public boolean isHaveNoteWithSameNameUnderDirectory(String name, String dirId) {
       return !this.getNoteByNameAndDirId(
               name ,
               dirId
       ).isEmpty();
    }

    @Override
    public Note getNoteInfo(int noteId)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException {
        Note savedNote = this.getNoteById(noteId);
        Map<String,Claim> payload = this.tokenService.getPayloadFromRequest(request);

        if( payload != null && savedNote != null && !savedNote.getAccount().getId().equals(payload.get("userId").asString())  )
            throw new InvalidDataAccessException("Invalid account for access!");

        return savedNote;
    }

}
