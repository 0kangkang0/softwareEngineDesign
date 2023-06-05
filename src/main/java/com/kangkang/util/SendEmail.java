package com.kangkang.util;

import com.kangkang.pojo.ToEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
    private static JavaMailSender mailSender;
    private static String from;
    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        SendEmail.mailSender = mailSender;
    }
    @Value("${spring.mail.username}")
    public void setFrom(String from) {
        SendEmail.from = from;
    }
    public static void commonEmail(ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
