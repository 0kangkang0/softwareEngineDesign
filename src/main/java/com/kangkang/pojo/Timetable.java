package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("timetable")
public class Timetable {

  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String scheduled;
  private String actual;
  private String estimated;
  private int type;
}
