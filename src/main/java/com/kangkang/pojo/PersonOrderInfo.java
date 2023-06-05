package com.kangkang.pojo;

import lombok.Data;

@Data
public class PersonOrderInfo {
    private Integer num;
    private String orderNo;
    private Double price;
    private String name;
    private String idNumber;
    private String tel;
    private String flightNo;
    private String startCity;
    private String startIataCode;
    private String startPyName;
    private String arriveCity;
    private Integer status;
}
