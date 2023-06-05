package com.kangkang.web;

import com.kangkang.pojo.LoginRequired;
import com.kangkang.pojo.Result;
import com.kangkang.pojo.User;
import com.kangkang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserControl {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PostMapping
    public Result Login(@RequestBody User user, HttpServletResponse httpServletResponse, HttpSession httpSession){
        user = userService.getUserByUsernameAndPassword(user);
        if(user==null)return Result.infoError("用户名或密码错误");
        Cookie cookie = new Cookie("userid", user.getId());
        httpSession.setAttribute("userid", user.getId());
        httpServletResponse.addCookie(cookie);
        return Result.ok();
    }

    /**
     * 更改用户信息
     * @param user 用户信息
     */
    @LoginRequired
    @PutMapping
    public void changeInfo(@RequestBody User user){
        userService.changeInfo(user);
    }
}
