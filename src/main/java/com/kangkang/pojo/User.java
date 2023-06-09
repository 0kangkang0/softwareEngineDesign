package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String username;
  private String password;
  private String logo;
  private String checkCode;
}
