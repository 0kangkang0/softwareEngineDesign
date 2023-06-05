package com.kangkang.service;

import com.kangkang.pojo.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    boolean buyTicket(Orders order);

    List<PersonOrderInfo> queryPersonTicket(String userId);

    PageBean<OrderManager> getOrderInfo(Integer currentPage, Integer pageSize, Orders order);

    Orders getOneOrder(String id);

    Result updateOrder(Orders orders);

    void deleteOrder(String id);
}
