package com.kangkang.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderManager extends Orders{
    private Ticket ticket;
    private Route route;
}
