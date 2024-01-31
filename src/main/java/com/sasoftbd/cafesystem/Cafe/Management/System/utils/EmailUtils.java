package com.sasoftbd.cafesystem.Cafe.Management.System.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
}
