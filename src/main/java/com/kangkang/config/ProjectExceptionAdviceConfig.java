package com.kangkang.config;

import com.kangkang.pojo.Result;
import com.kangkang.pojo.ToEmail;
import com.kangkang.util.SendEmail;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ProjectExceptionAdviceConfig {
//   统一异常处理
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        if(!(ex instanceof MailException)) {
            ToEmail toEmail = new ToEmail();
//            发送给谁
            toEmail.setTos(new String[]{"xxx@qq.com"});
            toEmail.setContent(ex.getMessage()+"\n"+Arrays.toString(ex.getStackTrace()));
            toEmail.setSubject("出bug啦");
            SendEmail.commonEmail(toEmail);
        }
        return Result.error(ex.getMessage());
    }
}
