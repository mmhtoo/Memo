package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.entity.Account;
import com.mmhtoo.note.entity.OTP;
import com.mmhtoo.note.repository.OTPRepo;
import com.mmhtoo.note.service.IEmailService;
import com.mmhtoo.note.service.IOTPService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OTPService implements IOTPService {

    private final IEmailService emailService;
    private final OTPRepo otpRepo;

    @Override
    public int generateOTP() {
        StringBuilder otp = new StringBuilder();

        for(int i = 1 ; i <= 5 ; i++ ) {
            otp.append((int) (Math.random() * 10));
        }

        return Integer.parseInt(otp.toString());
    }

    @Override
    public void send(Account sendTo, int otp) throws IOException, MessagingException {
        OTP savedOTP = this.saveOTP(this.generateOTP(),sendTo);

        ClassLoader loader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(loader.getResource("mail-template.txt")).getFile());

        String content = Files.readString(file.toPath())
                .replace("{{NAME}}",sendTo.getUsername())
                .replace("{{OTP}}",String.valueOf(otp))
                .replace("{{LINK}}","http://localhost:9000/api/accounts/verify" + "?email="+sendTo.getEmail()+"&code="+savedOTP.getOtp())
                .replace("{{REMARK}}","The link and OTP included in this email will expire after next 5 minutes!");

        this.emailService.sendEmail(
                sendTo.getEmail() ,
                "Sending verification for account registration!",
                content
        );
    }

    @Override
    public OTP saveOTP(int otp, Account account) {
        OTP otpObj = OTP.builder()
                .account(account)
                .otp(otp)
                .createdDate(LocalDateTime.now())
                .hasVerified(false)
                .expiredDate(LocalDateTime.now().plusMinutes(5))
                .build();
        return this.otpRepo.save(otpObj);
    }

    @Override
    public OTP getOTPByAccountIdAndOTP(String accountId, int otp) {
        return this.otpRepo.findByAccountIdAndOtp(accountId,otp)
                .orElse(null);
    }

    @Override
    public boolean validateOTP(String accountId, int otp) {
        OTP savedOtp = this.getOTPByAccountIdAndOTP(accountId,otp);

        if( savedOtp == null || savedOtp.getExpiredDate().isBefore(LocalDateTime.now()))
            return false;

        savedOtp.setHasVerified(true);
        savedOtp.setUpdatedDate(LocalDateTime.now());

        this.otpRepo.save(savedOtp);

        return true;
    }

}
