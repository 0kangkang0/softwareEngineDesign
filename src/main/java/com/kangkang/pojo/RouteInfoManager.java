package com.kangkang.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class RouteInfoManager extends Route{
    private Timetable arriveTime;
    private Timetable startTime;
    private CityInfo arriveCity;
    private CityInfo startCity;

}
