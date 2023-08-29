package com.mmhtoo.note.service;

import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.OTP;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IOTPService {

    int generateOTP();

    void send(String sendTo,int otp) throws IOException, MessagingException;

    OTP saveOTP(int otp, Account accountId);

}
