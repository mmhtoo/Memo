package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.AccountHistory;
import com.mmhtoo.note.enumeration.HistoryType;
import com.mmhtoo.note.repository.AccountHistoryRepo;
import com.mmhtoo.note.service.IAccountHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AccountHistoryService implements IAccountHistoryService  {

    private final AccountHistoryRepo accountHistoryRepo;


    @Override
    public AccountHistory saveHistory(
            HistoryType historyType ,
            String description ,
            Account account
    ) {
        AccountHistory accountHistory = AccountHistory.builder()
                .account(account)
                .historyType(historyType)
                .description(description)
                .createdDate(LocalDateTime.now())
                .build();
        return this.accountHistoryRepo.save(accountHistory);
    }
}
