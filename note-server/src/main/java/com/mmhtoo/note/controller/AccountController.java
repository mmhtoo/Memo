package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
import com.mmhtoo.note.mapper.AccountMapper;
import com.mmhtoo.note.service.IAccountService;
import com.mmhtoo.note.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AccountController extends BaseController {

    private final IAccountService accountService;

    @CheckBinding
    @PostMapping( value = "${api.accounts.register}" )
    public ResponseEntity<AppResponse> signupAccount(
            @Valid @RequestBody RegisterReqDTO accountReqDTO ,
            BindingResult bindingResult
    ) throws DuplicateEntityException, NeedVerificationException {

        Account savedAccount = this.accountService.createNewAccount(accountReqDTO);
        return ResponseUtil.dataResponse(
                HttpStatus.CREATED ,
                "Successfully registered account, Please verify your account in your email!",
                AccountMapper.accountToAccountResDto(savedAccount)
        );

    }

}
