package com.kangkang.service.impl;

import com.kangkang.mapper.*;
import com.kangkang.pojo.*;
import com.kangkang.service.TicketService;
import com.kangkang.util.GetInfoFromXieChen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TimetableMapper timetableMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PlatformTransactionManager txManager;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据出发城市、到达城市、出发时间获得机票数据
     *
     * @param queryTicketInfo 查询信息
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public List<TickInfoS> getTicketByStartArriveTime(QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startT = format.format(queryTicketInfo.getTime());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(queryTicketInfo.getTime());
        calendar.add(Calendar.DATE, 1);
        String endT = format.format(calendar.getTime());
        List<Route> routes = routeMapper.selectByTimeStartEnd(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT);
        if (routes == null || routes.size() == 0) {
            GetInfoFromXieChen.getInfo(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), queryTicketInfo.getTime());
            routes = routeMapper.selectByTimeStartEnd(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT);
        }
        ArrayList<TickInfoS> tickInfoS = new ArrayList<>();
        int idx = 1;
        //遍历所有路线信息
        for (Route r : routes) {
            if (!r.getStatus().equals("计划中")) {
                continue;
            }
            TickInfoS tickInfoS1 = new TickInfoS();
            String id = r.getId();
            List<Ticket> tickets = ticketMapper.selectByRouteId(Long.parseLong(id));
//            时间信息
            Timetable endTime = timetableMapper.selectById(r.getArriveTimeId());
            Timetable startTime = timetableMapper.selectById(r.getStartTimeId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//           到达时间
            long arrive = simpleDateFormat.parse(endTime.getScheduled()).getTime() - simpleDateFormat.parse(startTime.getScheduled()).getTime();

            tickets.sort(Comparator.comparingDouble(Ticket::getPrice));

            long hours = arrive / (1000 * 60 * 60);
            long minutes = (arrive - hours * (1000 * 60 * 60)) / (1000 * 60);

            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
            tickInfoS1.setNum(idx++);
            tickInfoS1.setMinPrice(tickets.get(0).getPrice());
            tickInfoS1.setNo(r.getAircraftCode());
            tickInfoS1.setStartTime(format1.format(simpleDateFormat.parse(startTime.getScheduled())));
            tickInfoS1.setArriveTime(hours + "小时" + minutes + "分钟");
            tickInfoS1.setEndTime(format1.format(simpleDateFormat.parse(endTime.getScheduled())));
            CityInfo startCity = cityMapper.selectByIataApCode(r.getStartAirportCode());
            tickInfoS1.setStartAirport(startCity.getCityPyName());
            CityInfo endCity = cityMapper.selectByIataApCode(r.getArriveAirportCode());
            tickInfoS1.setEndAirport(endCity.getCityPyName());
            tickInfoS1.setPrices(tickets);
            tickInfoS.add(tickInfoS1);
        }
        tickInfoS.sort(Comparator.comparingDouble(TickInfoS::getMinPrice));
        return tickInfoS;
    }

    @Override
    public OneTicketInfo getTicketById(Long id) {
        Ticket ticket = ticketMapper.selectById(id);
        String routeId = ticket.getRouteId();
        Route route = routeMapper.selectById(routeId);
        String startTimeId = route.getStartTimeId();
        Timetable startTime = timetableMapper.selectById(startTimeId);
        String arriveTimeId = route.getArriveTimeId();
        Timetable arriveTime = timetableMapper.selectById(arriveTimeId);
        OneTicketInfo oneTicketInfo = new OneTicketInfo();
        CityInfo arriveCity = cityMapper.selectByIataApCode(route.getArriveAirportCode());
        CityInfo startCity = cityMapper.selectByIataApCode(route.getStartAirportCode());
        //航程用时
        long useTimeStamp = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            useTimeStamp = dateFormat.parse(arriveTime.getScheduled()).getTime() - dateFormat.parse(startTime.getScheduled()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long hours = useTimeStamp / (1000 * 60 * 60);
        long minutes = (useTimeStamp - hours * (1000 * 60 * 60)) / (1000 * 60);
        oneTicketInfo.setId(String.valueOf(id));
        oneTicketInfo.setEndTime(arriveTime.getScheduled());
        oneTicketInfo.setStartTime(startTime.getScheduled());
        oneTicketInfo.setArriveTime(String.format("%02d小时%02d分钟", hours, minutes));
        oneTicketInfo.setStartAirport(startCity.getCityPyName() + "-" + startCity.getCnName());
        oneTicketInfo.setEndAirport(arriveCity.getCityPyName() + "-" + arriveCity.getCnName());
        oneTicketInfo.setFlightNo(route.getAircraftCode());
        oneTicketInfo.setPrice(ticket.getPrice());
        oneTicketInfo.setFromTo(arriveCity.getCityPyName() + "-" + arriveCity.getCityPyName());
        oneTicketInfo.setType(ticket.getType());
        return oneTicketInfo;
    }

    @Override
    public void logicDeleteById(String routerId) {
        ticketMapper.logicDeleteById(Long.parseLong(routerId));
    }

    @Override
    public void changeTicket(OrderTicketNo orderTicketNo) {
        String ticketNo = orderTicketNo.getTicketNo();
        String orderNo = orderTicketNo.getOrderNo();
        Orders orders = orderMapper.selectById(orderNo);
        Ticket ticket = ticketMapper.selectById(ticketNo);
        //开启事务
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            System.out.println(ticket);
//          新的机票数量-1
            ticket.setNum(ticket.getNum() - 1);
//          新订单
            Orders newOrder = new Orders();
            newOrder.setUserId(orders.getUserId());
            newOrder.setTicketId(ticket.getId());
            newOrder.setName(orders.getName());
            newOrder.setIdNumber(orders.getIdNumber());
            newOrder.setTel(orders.getTel());
            newOrder.setMail(orders.getMail());
            newOrder.setStatus(1);
//          旧订单设置状态为改签
            orders.setStatus(5);
//          就机票数据+1
            Ticket oddTicket = ticketMapper.selectById(orders.getTicketId());
            oddTicket.setNum(oddTicket.getNum() + 1);
//           修改和增加
            ticketMapper.updateById(oddTicket);
            ticketMapper.updateById(ticket);
            orderMapper.updateById(orders);
            orderMapper.insert(newOrder);
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
        txManager.commit(status);
    }

    @Override
    public PageBean<TicketManager> getManagerTicket(QueryTicketInfo queryTicketInfo, Integer currentPage, Integer pageSize) {
        //        时间数据
        String startT = null, endT = null;
        if (queryTicketInfo.getTime() != null) {
            startT = format.format(queryTicketInfo.getTime());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(queryTicketInfo.getTime());
            calendar.add(Calendar.DATE, 1);
            endT = format.format(calendar.getTime());
        }
//        分页数据
        Integer begin = (currentPage - 1) * pageSize;
//        处理数据
        List<TicketManager> ticketManagers = routeMapper.selectTicketManagerPageByInfo(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT, begin, pageSize);
        Integer total = routeMapper.selectCountByInfo(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT);
        for (TicketManager ticketManager : ticketManagers) {
//            获得基础信息
            CityInfo startCity = cityMapper.selectByIataApCode(ticketManager.getStartAirportCode());
            CityInfo arriveCity = cityMapper.selectByIataApCode(ticketManager.getArriveAirportCode());
            Timetable startTime = timetableMapper.selectById(ticketManager.getStartTimeId());
            Timetable arriveTime = timetableMapper.selectById(ticketManager.getArriveTimeId());
//            设置值
            ticketManager.setStartTime(startTime);
            ticketManager.setArriveTime(arriveTime);
            ticketManager.setStartCity(startCity);
            ticketManager.setArriveCity(arriveCity);
            ticketManager.setTickets(ticketMapper.selectAllTicketByRouterId(ticketManager.getId()));
        }
        return new PageBean<>(total, ticketManagers);
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketMapper.insert(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketMapper.updateById(ticket);
    }

    @Override
    public void deleteTicket(String id) {
        cityMapper.deleteById(id);
    }

    @Override
    public TicketManager getOneTicketManager(String id) {
        Ticket ticket = ticketMapper.selectById(id);
        if (ticket == null) return null;
        TicketManager ticketManager = routeMapper.selectTicketManagerById(ticket.getRouteId());
        ticketManager.setStartCity(cityMapper.selectByIataApCode(ticketManager.getStartAirportCode()));
        ticketManager.setArriveCity(cityMapper.selectByIataApCode(ticketManager.getArriveAirportCode()));
        ticketManager.setArriveTime(timetableMapper.selectById(ticketManager.getArriveTimeId()));
        ticketManager.setStartTime(timetableMapper.selectById(ticketManager.getStartTimeId()));
        ticketManager.setTickets(List.of(ticket));
        return ticketManager;
    }
}
