package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepo extends JpaRepository<AccountHistory,Integer> {
}
