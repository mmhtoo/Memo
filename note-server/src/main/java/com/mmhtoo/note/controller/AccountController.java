package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.AccountRegisterReqDTO;
import com.mmhtoo.note.dto.response.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController extends BaseController {

    @CheckBinding
    @PostMapping( value = "${api.accounts.register}" )
    public ResponseEntity<AppResponse> signupAccount(
            @Valid @RequestBody AccountRegisterReqDTO accountReqDTO ,
            BindingResult bindingResult
    ){
        return null;
    }

}
