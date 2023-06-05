package com.kangkang.service;

import com.kangkang.pojo.Manager;

public interface ManagerService {
    Manager selectByUsernamePassword(Manager manager);
}
