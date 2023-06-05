package com.kangkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("route")
public class Route {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String aircraftCode;
    private String startAirportCode;
    private String arriveAirportCode;
    private String arriveTimeId;
    private String startTimeId;
    private String routeCode;
    private String status;
}
