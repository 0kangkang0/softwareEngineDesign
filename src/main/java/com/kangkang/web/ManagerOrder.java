package com.kangkang.web;

import com.kangkang.pojo.Manager;
import com.kangkang.pojo.Result;
import com.kangkang.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manager")
public class ManagerOrder {
    @Autowired
    private ManagerService managerService;
    @PostMapping
    public Result login(@RequestBody Manager manager, HttpSession session){
        Manager manager1 = managerService.selectByUsernamePassword(manager);
        if(manager1==null){
            return Result.notLogin("用户信息有误");
        }else{
            session.setAttribute("manager",manager1.getId());
            return new Result(302,"/manager.html");
        }
    }
}
