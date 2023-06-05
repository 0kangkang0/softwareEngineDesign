package com.kangkang.service.impl;

import com.kangkang.mapper.*;
import com.kangkang.pojo.*;
import com.kangkang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private TimetableMapper timetableMapper;
    public boolean buyTicket(Orders order){
        //开启事务
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            order.setStatus(1);
            orderMapper.insert(order);
            Ticket ticket = ticketMapper.selectById(order.getTicketId());
            ticket.setNum(ticket.getNum()-1);
            ticketMapper.updateById(ticket);
        } catch (Exception e) {
            txManager.rollback(status);
            e.printStackTrace();
            return false;
        }
        txManager.commit(status);
        return true;
    }

    @Override
    public List<PersonOrderInfo> queryPersonTicket(String userId) {
        List<Orders> orders = orderMapper.selectByUserId(Long.valueOf(userId));
        ArrayList<PersonOrderInfo> personOrderInfos = new ArrayList<>();
        int idx=0;
        for (Orders order : orders) {
            PersonOrderInfo personOrderInfo = new PersonOrderInfo();
            String ticketId = order.getTicketId();
            Ticket ticket = ticketMapper.selectById(ticketId);
            Route route = routeMapper.selectById(ticket.getRouteId());
            CityInfo startCity = cityMapper.selectByIataApCode(route.getStartAirportCode());
            CityInfo arriveCity = cityMapper.selectByIataApCode(route.getArriveAirportCode());
            personOrderInfo.setNum(idx++);
            personOrderInfo.setOrderNo(order.getId());
            personOrderInfo.setPrice(ticket.getPrice());
            personOrderInfo.setName(order.getName());
            personOrderInfo.setIdNumber(order.getIdNumber());
            personOrderInfo.setTel(order.getTel());
            personOrderInfo.setFlightNo(route.getAircraftCode());
            personOrderInfo.setStartCity(startCity.getCityPyName());
            personOrderInfo.setStartIataCode(startCity.getIataApCode());
            personOrderInfo.setStartPyName(startCity.getCityEnglishName());
            personOrderInfo.setArriveCity(arriveCity.getCityPyName());
            personOrderInfo.setStatus(order.getStatus());
            personOrderInfos.add(personOrderInfo);
        }
        return personOrderInfos;
    }

    /**
     * 获得后台的定当数据
     * @return
     */
    @Override
    public PageBean<OrderManager> getOrderInfo(Integer currentPage, Integer pageSize, Orders queryOrder){
        Integer begin=(currentPage-1)*pageSize;
        if(queryOrder.getName()!=null&&queryOrder.getName().length()>0){
            queryOrder.setName("%"+queryOrder.getName()+"%");
        }
        if(queryOrder.getIdNumber()!=null&&queryOrder.getIdNumber().length()>0){
            queryOrder.setIdNumber("%"+queryOrder.getIdNumber()+"%");
        }
        if(queryOrder.getTel()!=null&&queryOrder.getTel().length()>0){
            queryOrder.setTel("%"+queryOrder.getTel()+"%");
        }
//        数据
        Integer total = orderMapper.selectAllOrderManagerCount(queryOrder);
        List<OrderManager> orders = orderMapper.selectAllOrderManager(queryOrder,begin,pageSize);
        for (OrderManager order : orders) {
//            获得机票信息
            Ticket ticket = ticketMapper.selectById(order.getTicketId());
            RouteInfoManager route = routeMapper.selectRouterInfoManagerById(ticket.getRouteId());
            CityInfo startCity = cityMapper.selectByIataApCode(route.getStartAirportCode());
            CityInfo arriveCity = cityMapper.selectByIataApCode(route.getArriveAirportCode());
            Timetable startTime = timetableMapper.selectById(route.getStartTimeId());
            Timetable arriveTime = timetableMapper.selectById(route.getArriveTimeId());
            route.setStartTime(startTime);
            route.setArriveTime(arriveTime);
            route.setStartCity(startCity);
            route.setArriveCity(arriveCity);
            order.setRoute(route);
            order.setTicket(ticket);
        }
        return new PageBean<>(total,orders);
    }

    @Override
    public Orders getOneOrder(String id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Result updateOrder(Orders orders) {
        if(ticketMapper.selectById(orders.getTicketId())==null){
            return Result.infoError("输入的机票编码有误");
        }
        orderMapper.updateById(orders);
        return Result.ok();
    }

    @Override
    public void deleteOrder(String id) {
        orderMapper.deleteById(id);
    }
}
