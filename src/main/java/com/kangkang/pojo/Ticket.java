package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ticket")
public class Ticket {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private Double price;
  private String type;
  private Long num;
  private String routeId;
}
