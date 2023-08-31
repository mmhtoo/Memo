package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
