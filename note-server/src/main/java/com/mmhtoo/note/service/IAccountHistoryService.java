package com.mmhtoo.note.service;

import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.AccountHistory;
import com.mmhtoo.note.enumeration.HistoryType;

public interface IAccountHistoryService {

    AccountHistory saveHistory(HistoryType historyType, String description, Account account);

}
