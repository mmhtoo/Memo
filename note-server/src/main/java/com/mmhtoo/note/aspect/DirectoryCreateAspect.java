package com.mmhtoo.note.aspect;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.service.IDirectoryService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Aspect
@Component
@AllArgsConstructor
public class DirectoryCreateAspect {

    private final IDirectoryService directoryService;

    @AfterReturning(
          value =  "@annotation(com.mmhtoo.note.annotation.CreateDirectoryAfterSuccess)" ,
            returning = "verifiedAccount"
    )
    public void createHomeDirectoryAdvice(
            JoinPoint joinPoint ,
            Object verifiedAccount
    ) throws DuplicateEntityException, InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Account savedAccount = (Account) verifiedAccount;
        DirectoryCreateReqDTO directoryCreateReqDTO = new DirectoryCreateReqDTO();
        directoryCreateReqDTO.setName(savedAccount.getUsername()+"'s Home");
        directoryCreateReqDTO.setParentDirId(null);
        directoryCreateReqDTO.setAccountId(savedAccount.getId());
        this.directoryService.createNewDirectory(
                directoryCreateReqDTO
        );
    }



}
