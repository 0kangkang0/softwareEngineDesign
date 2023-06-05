package com.kangkang.service;

import com.kangkang.pojo.Result;
import com.kangkang.pojo.User;

public interface UserService {
    Result add(User user);
    User getUserById(Long userid);
    User getUserByUsernameAndPassword(User user);
    void changeInfo(User user);
}
