package com.kangkang.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TickInfoS {
    private Integer num;
    private String no;
    private String startTime;
    private String startAirport;
    private String arriveTime;
    private String endTime;
    private String endAirport;
    private Double minPrice;
    private List<Ticket>Prices;
}
