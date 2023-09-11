package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.dto.request.DirectoryUpdateReqDTO;
import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.mapper.DirectoryMapper;
import com.mmhtoo.note.service.IDirectoryService;
import com.mmhtoo.note.service.ITokenService;
import com.mmhtoo.note.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping( value = "${api.directories.root}" )
@AllArgsConstructor
public class DirectoryController extends BaseController {

    private final IDirectoryService directoryService;
    private final ITokenService tokenService;

    @CheckBinding
    @PostMapping
    @Transactional
    public ResponseEntity<AppResponse> createNewDirectory(
          @Valid @RequestBody DirectoryCreateReqDTO directoryCreateReqDTO ,
          BindingResult bindingResult
    ) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException, DuplicateEntityException {
        Directory savedDirectory = this.directoryService.createNewDirectory(
                directoryCreateReqDTO
        );
        return ResponseUtil.dataResponse(
                HttpStatus.OK,
                "Successfully created new directory!",
                DirectoryMapper.directoryToDirectoryResponseDTO(savedDirectory)
        );
    }

    @GetMapping( value = "${api.directories.targetDir}" )
    public ResponseEntity<AppResponse> getDirectoryInfo(
            @PathVariable( value = "directoryId" ) String directoryId
    ) throws InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseUtil.dataResponse(
                HttpStatus.OK ,
                "Success!",
                DirectoryMapper.directoryToDirectoryResponseDTO(
                        this.directoryService.getDirectoryInfoById(directoryId)
                )
        );
    }

    @CheckBinding
    @PutMapping( value = "${api.directories.targetDir}")
    @Transactional
    public ResponseEntity<AppResponse> updateDirectory(
            @Valid @RequestBody DirectoryUpdateReqDTO directoryUpdateReqDTO ,
            BindingResult bindingResult ,
            @PathVariable( value = "directoryId") String directoryId
    ) throws InvalidDataAccessException, DuplicateEntityException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Directory updatedDirectory = this.directoryService.updateDirectory(
                directoryUpdateReqDTO , directoryId

        );
        return ResponseUtil.dataResponse(
                HttpStatus.OK,
                "Successfully updated!",
                DirectoryMapper.directoryToDirectoryResponseDTO(
                        updatedDirectory
                )
        );
    }

    @GetMapping
    public ResponseEntity<AppResponse> getAllDirectoriesOfAccount() throws InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseUtil
                .dataResponse(
                        HttpStatus.OK,
                        "Success!",
                        this.directoryService.getDirectoriesOfAccount()
                                .stream().map(DirectoryMapper::directoryToDirectoryResponseDTO)
                );
    }


}

