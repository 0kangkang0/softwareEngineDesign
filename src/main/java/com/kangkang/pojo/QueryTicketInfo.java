package com.kangkang.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.regex.Pattern;

@Data
public class QueryTicketInfo {
    String startCity;
    String endCity;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    Date time;
}
