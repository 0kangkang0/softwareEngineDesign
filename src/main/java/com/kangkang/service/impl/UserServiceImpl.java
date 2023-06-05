package com.kangkang.service.impl;

import com.kangkang.mapper.UserMapper;
import com.kangkang.pojo.Result;
import com.kangkang.pojo.User;
import com.kangkang.service.UserService;
import com.kangkang.util.GetImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result add(User user) {
        User user1 = userMapper.selectByName(user.getUsername());
        if(user1!=null){
            return Result.infoError("用户名重复");
        }
        try{
            String s = GetImage.generateImg(user.getUsername(), user.getUsername());
            int i = s.lastIndexOf('\\');
//            System.out.println(i);
            s = s.substring(i+1);
            user.setLogo(s);
            userMapper.insertUserByUsernamePassword(user);
        }catch (IOException e){
            e.printStackTrace();
            return Result.error("生成头像图片出错");
        }
        return Result.ok();
    }

    @Override
    public User getUserById(Long userid) {
        return userMapper.selectById(userid);
    }

    @Override
    public User getUserByUsernameAndPassword(User user) {
        return userMapper.selectByNamePassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void changeInfo(User user) {
        userMapper.updateById(user);
    }
}
