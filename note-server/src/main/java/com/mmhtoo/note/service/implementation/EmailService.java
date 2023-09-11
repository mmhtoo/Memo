package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String sendTo, String subject, String body)
            throws MessagingException {

        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        mimeMessageHelper.setTo(sendTo);
        mimeMessageHelper.setFrom("memo@note.com");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body,true);

        this.mailSender.send(mimeMessage);
    }

}
