package com.kangkang.web;

import com.kangkang.pojo.*;
import com.kangkang.service.OrderService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControl {
    @Autowired
    private OrderService orderService;

    /**
     * 买机票
     * @param order
     * @return
     */
    @LoginRequired
    @PostMapping
    public Result buyTicket(@RequestBody Orders order) {
        if(orderService.buyTicket(order)){
            return Result.ok("/finish.html");
        }
        return Result.error("出错了");
    }

    /**
     * 查询个人订单
     * @param session
     * @return
     */
    @LoginRequired
    @GetMapping
    public Result queryMyOrder(HttpSession session){
        String userid = (String) session.getAttribute("userid");
        if(userid == null){
            return Result.notLogin("登录信息超时，请重新登录");
        }
        List<PersonOrderInfo> personOrderInfos = orderService.queryPersonTicket(userid);
        return Result.ok(personOrderInfos);
    }
    @ManagerRequired
    @PostMapping("/manager/{currentPage}/{pageSize}")
    public PageBean<OrderManager> getOrder(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody Orders order){
        return orderService.getOrderInfo(currentPage, pageSize, order);
    }
    @ManagerRequired
    @GetMapping("/manager/{id}")
    public Orders getOrderInfo(@PathVariable String id){
        return orderService.getOneOrder(id);
    }

    /**
     * 修改机票
     * @param orders
     * @return
     */
    @ManagerRequired
    @PutMapping("/manager")
    public Result updateOrder(@RequestBody Orders orders){
        if(orders.getTel()==null||orders.getTel().length()==0||orders.getName()==null||orders.getName().length()==0||orders.getIdNumber()==null||orders.getIdNumber().length()==0||orders.getTicketId()==null||orders.getTicketId().length()==0||orders.getStatus()==null){
            return Result.infoError("输入的信息不全，请重新输入");
        }
        return orderService.updateOrder(orders);
    }

    /**
     * 删除机票
     * @param id
     */
    @ManagerRequired
    @DeleteMapping("/manager/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }
}
