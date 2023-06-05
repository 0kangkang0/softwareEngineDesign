package com.kangkang.pojo;

import lombok.Data;

@Data
public class OneTicketInfo {
    private String id;
    private String flightNo;
    private String startTime;
    private String arriveTime;
    private String endTime;
    private String startAirport;
    private String endAirport;
    private Double price;
    private String fromTo;
    private String type;
}
