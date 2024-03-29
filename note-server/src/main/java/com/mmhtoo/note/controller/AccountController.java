package com.mmhtoo.note.controller;

import com.mmhtoo.note.annotation.CheckBinding;
import com.mmhtoo.note.dto.request.LoginReqDTO;
import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.dto.response.AppResponse;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
import com.mmhtoo.note.exception.custom.RepeatedVerificationException;
import com.mmhtoo.note.mapper.AccountMapper;
import com.mmhtoo.note.service.IAccountService;
import com.mmhtoo.note.service.ITokenService;
import com.mmhtoo.note.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "${api.accounts.root}")
@AllArgsConstructor
public class AccountController extends BaseController {

    private final IAccountService accountService;
    private final ITokenService tokenService;

    @CheckBinding
    @PostMapping( value = "${api.accounts.register}" )
    @Transactional
    public ResponseEntity<AppResponse> signupAccount(
            @Valid @RequestBody RegisterReqDTO accountReqDTO ,
            BindingResult bindingResult
    ) throws DuplicateEntityException, NeedVerificationException, MessagingException, IOException {

        Account savedAccount = this.accountService.createNewAccount(accountReqDTO);
        return ResponseUtil.dataResponse(
                HttpStatus.CREATED ,
                "Successfully registered account, Please verify your account in your email!",
                AccountMapper.accountToAccountResDto(savedAccount)
        );

    }

    @CheckBinding
    @PostMapping( value = "${api.accounts.login}" )
    public ResponseEntity<AppResponse> signinAccount(
            @Valid @RequestBody LoginReqDTO loginReqDTO ,
            BindingResult bindingResult
    ) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        Account account = this.accountService.authenticate(loginReqDTO);

        String token = this.tokenService.generate(
                this.accountService.getTokenPayload(account)
        );

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,token);
        httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization");

        return ResponseUtil.dataResponse(
                HttpStatus.OK ,
                "Successfully login!",
                AccountMapper.accountToAccountResDto(account) ,
                httpHeaders
        ) ;
    }

    @GetMapping( value = "${api.accounts.verify}")
    @Transactional
    public ResponseEntity<AppResponse> verifyAccount(
            @RequestParam( value = "email" ) String email ,
            @RequestParam( value = "code" ) int code
    ) throws InvalidDataAccessException, RepeatedVerificationException {
        Account savedAccount = this.accountService.verifyAccount(email,code);
        return ResponseUtil.dataResponse(
                HttpStatus.OK,
                "Successfully verified account with "+email+"!",
                AccountMapper.accountToAccountResDto(savedAccount)
        );
    }

    @GetMapping( value = "${api.accounts.accountInfo}")
    public ResponseEntity<AppResponse> getAccountInfo(
            @PathVariable( value = "accountId" ) String accountId
    ) throws InvalidDataAccessException {
        Account account = this.accountService.getAccountByAccountId(accountId);

        if( !account.isEnabled() )
            throw new InvalidDataAccessException("Invalid request for accessing account!");

        return ResponseUtil.dataResponse(
                HttpStatus.OK ,
                "Success!",
                AccountMapper.accountToAccountResDto(
                        this.accountService.getAccountByAccountId(accountId)
                )
        );
    }

    @PostMapping( value = "${api.accounts.logout}" )
    @Transactional
    public ResponseEntity<AppResponse> logout(
            HttpServletRequest request
    ) throws InvalidDataAccessException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        boolean success = this.accountService.logout(request);
        if(success)
            return ResponseUtil.dataResponse(
                    HttpStatus.OK ,
                    "Successfully logged out!",
                    "Successfully logged out!"
            );

        Map<String,String> errors = new HashMap<>();
        errors.put("error","Failed to logout!");

        return ResponseUtil.errorResponse(
            HttpStatus.BAD_REQUEST,
                "Failed to logout!",
                errors
        );
    }

}
