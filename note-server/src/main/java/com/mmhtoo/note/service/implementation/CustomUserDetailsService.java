package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.dto.UserPrincipal;
import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.repository.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> savedAccount = this.accountRepo.findByEmail(username);

        if(savedAccount.isEmpty())
            throw new UsernameNotFoundException("Bad Credentials!");

        if(!savedAccount.get().isEnabled())
            throw new BadCredentialsException("You need to verify your account with this email or you need to request to re-send verification email!");

        return new UserPrincipal(savedAccount.get());
    }

}
