package com.kangkang.web;


import com.kangkang.pojo.Result;
import com.kangkang.pojo.User;
import com.kangkang.service.UserService;
import com.kangkang.util.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/register")
public class RegisterServlet {
    @Autowired
    UserService userService;
    @GetMapping
    public void getCheckCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        HttpSession session = request.getSession();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        session.setAttribute("checkCodeGen",checkCode);
    }

    @PostMapping
    public Result Register(@RequestBody User user,HttpSession session){
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");
        if(!checkCodeGen.equalsIgnoreCase(user.getCheckCode())){
            return Result.infoError("验证码错误");
        }
        return userService.add(user);
    }

}
