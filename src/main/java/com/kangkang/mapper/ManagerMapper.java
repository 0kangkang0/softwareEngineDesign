package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("select * from software_engine_design.manager where username=#{username} and password=#{password}")
    Manager selectByUsernamePassword(Manager manager);
}
