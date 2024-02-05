package com.sasoftbd.cafesystem.Cafe.Management.System.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
@Slf4j
@Service
public class EmailUtils {

    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zakaria112203@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if(list != null && list.size() > 0){
            message.setCc(getCcArray(list));
        }
        mailSender.send(message);
    }


    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];

        for(int i=0; i < ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
     return cc;
    }

    public void forgetMail(String to, String subject, String password ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("sdfsdfsdf@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
       String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";

       message.setContent(htmlMsg, "text/html");
       mailSender.send(message);

    }
}
