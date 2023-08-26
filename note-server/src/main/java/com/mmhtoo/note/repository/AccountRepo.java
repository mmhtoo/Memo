package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,String> {

    Optional<Account> findByEmail(String email);

}
