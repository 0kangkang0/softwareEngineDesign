package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city_info")
public class CityInfo {

  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String airportEnglishName;
  private String airportPyName;
  private String cityPyName;
  private String cityEnglishName;
  private String cnName;
  private String code;
  private String iataApCode;

  public CityInfo() {
  }

  public CityInfo(String airportEnglishName, String airportPyName, String cityPyName, String cityEnglishName, String cnName, String code, String iataApCode) {
    this.airportEnglishName = airportEnglishName;
    this.airportPyName = airportPyName;
    this.cityPyName = cityPyName;
    this.cityEnglishName = cityEnglishName;
    this.cnName = cnName;
    this.code = code;
    this.iataApCode = iataApCode;
  }
}
