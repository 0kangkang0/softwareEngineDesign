package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into software_engine_design.user (id,username,password,logo) values (#{id},#{username},#{password},#{logo})")
    void insertUserByUsernamePassword(User user);
    @Select("select * from software_engine_design.user where username=#{username}")
    User selectByName(String username);
    @Select("select * from software_engine_design.user where username=#{username} and password=#{password}")
    User selectByNamePassword(String username,String password);
    @Select("select * from software_engine_design.user where id=#{id}")
    User selectById(Long id);
}
