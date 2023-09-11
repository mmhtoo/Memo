package com.mmhtoo.note.service;

import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.OTP;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IOTPService {

    /*
     * will generate OTP with length 6
     */
    int generateOTP();

    /*
     * will send OTP to target account
     */
    void send(Account sendTo,int otp) throws IOException, MessagingException;

    /*
     * for saving OTP in database
     */
    OTP saveOTP(int otp, Account accountId);

    /*
     * for getting OTP by account id and otp
     */
    OTP getOTPByAccountIdAndOTP(String accountId,int otp);

    /*
     * for checking otp is valid or not
     */
    boolean validateOTP(String accountId,int otp);

}
