package com.mmhtoo.note.aspect;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.service.IDirectoryService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
    ) throws DuplicateEntityException {
        Account savedAccount = (Account) verifiedAccount;
        DirectoryCreateReqDTO directoryCreateReqDTO = new DirectoryCreateReqDTO();
        directoryCreateReqDTO.setAccountId(savedAccount.getId());
        directoryCreateReqDTO.setName(savedAccount.getUsername()+"'s Home");
        directoryCreateReqDTO.setParentDirId(null);
        this.directoryService.createNewDirectory(
                directoryCreateReqDTO
        );
    }

}
