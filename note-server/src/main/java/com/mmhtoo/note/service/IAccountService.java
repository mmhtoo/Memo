package com.mmhtoo.note.service;

import com.mmhtoo.note.dto.request.LoginReqDTO;
import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface IAccountService {

    /*
     * for checking duplicate email or not
     * @params{ email : String }
     * @return : boolean
     * @description - will return true if email has already been used, otherwise false
     */
    boolean isDuplicateEmail(String email);

    /*
     * for selecting Account entity by email
     * @params{ email : String }
     * @return : Account | null
     * @description : will return Account entity if there is account with that email, otherwise null
     */
    Account getAccountByEmail(String email);

    /*
     * for selecting Account entity by Account Id
     * @params{ accountId : String }
     * @return : Account | null
     * @description : will return Account entity if there is account with that email,otherwise null
     */
    Account getAccountByAccountId(String accountId) throws InvalidDataAccessException;

    /*
     * for authenticating Account
     * @params{ loginReqDTO : LoginReqDTO }
     * @return : Account | null
     * @description : will return Account entity if there is valid user, otherwise will throw UsernameNotFoundException
     */
    Account authenticate(LoginReqDTO loginReqDTO);

    /*
     * for creating new account
     * @params{ regsiterReqDTO : RegisterReqDTO }
     * @return : Account | null
     * @description : will return created Account entity if completed,
     *  will throw DuplicateEntityException if email is duplicate,
     *  after running this method will call to sendEmail Method
     */
    Account createNewAccount(RegisterReqDTO registerReqDTO) throws DuplicateEntityException, NeedVerificationException, MessagingException, IOException;

    /*
     * for getting payload object from account entity to use as payload in token
     * @params{ account : Account }
     * @return : Map<String,String>
     * @description : Will return key value map object payload
     */
    Map<String,String> getTokenPayload(Account account);

    /*
     * for verifying account registration
     * @params{ email : String , otp : String }
     * @return : Account | null
     * @description : will make enable in account true
     * if otp is true for that account and that otp is valid
     */
    Account verifyAccount(String email,String otp);

}
