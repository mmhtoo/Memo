package com.mmhtoo.note.service;

import javax.mail.MessagingException;

public interface IEmailService {

    void sendEmail(String sendTo, String subject, String body) throws MessagingException;

}
