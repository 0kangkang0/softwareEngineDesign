package com.kangkang.service.impl;

import com.kangkang.mapper.ManagerMapper;
import com.kangkang.pojo.Manager;
import com.kangkang.service.ManagerService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager selectByUsernamePassword(Manager manager) {
        return managerMapper.selectByUsernamePassword(manager);
    }
}
