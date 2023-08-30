package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.interfaces.Claim;
import com.mmhtoo.note.dto.request.LoginReqDTO;
import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.OTP;
import com.mmhtoo.note.enumeration.HistoryType;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
import com.mmhtoo.note.exception.custom.RepeatedVerificationException;
import com.mmhtoo.note.mapper.AccountMapper;
import com.mmhtoo.note.repository.AccountRepo;
import com.mmhtoo.note.service.IAccountHistoryService;
import com.mmhtoo.note.service.IAccountService;
import com.mmhtoo.note.service.IOTPService;
import com.mmhtoo.note.service.ITokenService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IOTPService otpService;
    private final ITokenService tokenService;
    private final IAccountHistoryService accountHistoryService;

    @Override
    public boolean isDuplicateEmail(String email) {
        return this.accountRepo.findByEmail(email).isPresent();
    }

    @Override
    public Account getAccountByEmail(String email) {
        return this.accountRepo.findByEmail(email)
                .orElse(null);
    }

    @Override
    public Account getAccountByAccountId(String accountId) throws InvalidDataAccessException {
        Optional<Account> optAccount = this.accountRepo.findById(accountId);

        if( optAccount.isEmpty() )
            throw new InvalidDataAccessException("Invalid request for accessing Account!");

        return optAccount.get();
    }

    @Override
    public Account authenticate(LoginReqDTO loginReqDTO) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqDTO.getEmail() ,
                        loginReqDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        return this.getAccountByEmail(loginReqDTO.getEmail());
    }

    @Override
    public Account createNewAccount(RegisterReqDTO registerReqDTO)
            throws DuplicateEntityException, NeedVerificationException, MessagingException, IOException {
        Account savedAccount = this.getAccountByEmail(registerReqDTO.getEmail());

        // checking this email is already used by other verified account
        if(savedAccount != null && savedAccount.isEnabled())
            throw new DuplicateEntityException("This email is already used by other account!");

        // checking this email has already registered, but has not verified yet
        if(savedAccount != null && !savedAccount.isEnabled())
            throw new NeedVerificationException("This account is already registered, you need to verify in your email for complete!");

        registerReqDTO.setPassword(this.passwordEncoder.encode(registerReqDTO.getPassword()));
        Account account = AccountMapper.registerReqDtoToAccount(registerReqDTO);
        account.setId(UUID.randomUUID().toString());
        account.setEnabled(false);
        account.setLocked(false);
        account.setJoinedDate(LocalDateTime.now());

        savedAccount = this.accountRepo.save(account);

        this.otpService.send(
                savedAccount ,
                this.otpService.generateOTP()
        );

        return savedAccount;
    }

    @Override
    public Map<String, String> getTokenPayload(Account account) {
        Map<String,String> payload = new HashMap<>();
        payload.put("userId",account.getId());
        payload.put("email",account.getEmail());
        payload.put("username",account.getUsername());
        return payload;
    }

    @Override
    public Account verifyAccount(String email, int otp) throws InvalidDataAccessException, RepeatedVerificationException {
       Account savedAccount = this.getAccountByEmail(email);

       // checking there is registered account with email or note
       if( savedAccount == null )
           throw new InvalidDataAccessException("Invalid email or OTP!");

       // checking that account is already enabled or not
       if( savedAccount.isEnabled() )
           throw new RepeatedVerificationException("This account has already verified!");

       if( !this.otpService.validateOTP(savedAccount.getId(),otp))
           throw new InvalidDataAccessException("Invalid email or OTP!");

       savedAccount.setUpdatedDate(LocalDateTime.now());
       savedAccount.setEnabled(true);
       this.accountRepo.save(savedAccount);

       return savedAccount;
    }

    @Override
    public boolean logout(HttpServletRequest request)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException {
        /*
         * it is sure that include jwt token because filter will check before executing this end point
         */
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION)
                .substring(7);

        Map<String, Claim> payload = this.tokenService
                .getPayloadFromToken(jwtToken);

        String userId = payload.get("userId").asString();
        Account savedAccount = this.getAccountByAccountId(userId);

        return this.accountHistoryService.saveHistory(
                HistoryType.LOGOUT ,
                savedAccount.getUsername() + " logged out at "+ LocalDateTime.now() ,
                savedAccount
        ) != null;
    }

}
