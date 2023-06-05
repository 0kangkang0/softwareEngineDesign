package com.kangkang.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class TicketManager extends RouteInfoManager {
    private List<Ticket> tickets;
}
