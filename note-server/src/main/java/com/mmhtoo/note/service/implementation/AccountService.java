package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.dto.request.LoginReqDTO;
import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.exception.custom.NeedVerificationException;
import com.mmhtoo.note.mapper.AccountMapper;
import com.mmhtoo.note.repository.AccountRepo;
import com.mmhtoo.note.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

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
    public Authentication authenticate(LoginReqDTO loginReqDTO) {
        return this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReqDTO.getEmail() ,
                        loginReqDTO.getPassword()
                )
        );
    }

    @Override
    @Transactional
    public Account createNewAccount(RegisterReqDTO registerReqDTO)
            throws DuplicateEntityException, NeedVerificationException {
        Account savedAccount = this.getAccountByEmail(registerReqDTO.getEmail());

        // checking this email is already used by other verified account
        if(savedAccount != null && savedAccount.getIsEnabled())
            throw new DuplicateEntityException("This email is already used by other account!");

        // checking this email has already registered, but has not verified yet
        if(savedAccount != null && !savedAccount.getIsEnabled())
            throw new NeedVerificationException("This account is already registered, you need to verify in your email for complete!");

        registerReqDTO.setPassword(this.passwordEncoder.encode(registerReqDTO.getPassword()));
        Account account = AccountMapper.registerReqDtoToAccount(registerReqDTO);
        account.setId(UUID.randomUUID().toString());
        account.setIsEnabled(false);
        account.setIsLocked(false);
        account.setJoinedDate(LocalDateTime.now());

        // TODO : send verification email

        return this.accountRepo.save(account);
    }

}
