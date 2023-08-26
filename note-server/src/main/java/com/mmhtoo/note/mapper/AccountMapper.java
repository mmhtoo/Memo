package com.mmhtoo.note.mapper;

import com.mmhtoo.note.dto.request.RegisterReqDTO;
import com.mmhtoo.note.dto.response.AccountResponseDTO;
import com.mmhtoo.note.entity.Account;

public class AccountMapper {

    public static Account registerReqDtoToAccount(RegisterReqDTO registerReqDTO) {
        return Account.builder()
                .username(registerReqDTO.getUsername())
                .email(registerReqDTO.getEmail())
                .password(registerReqDTO.getPassword())
                .build();
    }

    public static AccountResponseDTO accountToAccountResDto(Account account) {
        return AccountResponseDTO.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .password(account.getPassword())
                .isLocked(account.getIsLocked())
                .joinedDate(account.getJoinedDate())
                .updatedDate(account.getUpdatedDate())
                .build();
    }

}
