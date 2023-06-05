package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manager")
public class Manager {
  @TableId(type= IdType.ASSIGN_ID)
  private String id;
  private String username;
  private String password;

}
