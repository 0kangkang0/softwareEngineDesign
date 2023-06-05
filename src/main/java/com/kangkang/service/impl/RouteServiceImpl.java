package com.kangkang.service.impl;

import com.kangkang.mapper.CityMapper;
import com.kangkang.mapper.RouteMapper;
import com.kangkang.mapper.TimetableMapper;
import com.kangkang.pojo.*;
import com.kangkang.service.RouteService;
import com.kangkang.util.GetInfoFromXieChen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    TimetableMapper timetableMapper;
    @Autowired
    CityMapper cityMapper;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    public void insert(Route route) {
        routeMapper.insert(route);
    }

    @Override
    public List<Route> select(Route route) {
        return routeMapper.selectList(route);
    }

    @Override
    public List<RouterInfo> selectInfo(QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException {
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
        ArrayList<RouterInfo> routerInfos = new ArrayList<>();
        int idx = 0;
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Route route : routes) {
            RouterInfo routerInfo = new RouterInfo();
            Timetable startTable = timetableMapper.selectById(route.getStartTimeId());
            Timetable arriveTable = timetableMapper.selectById(route.getArriveTimeId());
            CityInfo startCityInfo = cityMapper.selectByIataApCode(route.getStartAirportCode());
            CityInfo arriveCityInfo = cityMapper.selectByIataApCode(route.getArriveAirportCode());
            routerInfo.setNum(idx++);
            routerInfo.setAirNo(route.getAircraftCode());
            routerInfo.setRoute(startCityInfo.getCityPyName() + "——" + arriveCityInfo.getCityPyName());
            routerInfo.setDate(dayFormat.format(dateFormat.parse(startTable.getScheduled())));
            routerInfo.setStartTime(timeFormat.format(dateFormat.parse(startTable.getScheduled())));
            routerInfo.setArriveTime(timeFormat.format(dateFormat.parse(arriveTable.getScheduled())));
            routerInfo.setStatus(route.getStatus());
            routerInfos.add(routerInfo);
        }
        return routerInfos;
    }

    /**
     * 获得管理页面的航线信息。
     *
     * @return 查询完成后的routeInfoManager
     */
    public PageBean<RouteInfoManager> getRouteInfo(QueryTicketInfo queryTicketInfo, Integer currentPage, Integer pageSize) {
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
        List<RouteInfoManager> routes = routeMapper.selectPageByInfo(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT, begin, pageSize);
        Integer count = routeMapper.selectCountByInfo(queryTicketInfo.getStartCity(), queryTicketInfo.getEndCity(), startT, endT);
        for (RouteInfoManager route : routes) {
            Timetable startTime = timetableMapper.selectById(route.getStartTimeId());
            Timetable arriveTime = timetableMapper.selectById(route.getArriveTimeId());
            CityInfo startCity = cityMapper.selectByIataApCode(route.getStartAirportCode());
            CityInfo arriveCity = cityMapper.selectByIataApCode(route.getArriveAirportCode());
            route.setStartTime(startTime);
            route.setArriveTime(arriveTime);
            route.setStartCity(startCity);
            route.setArriveCity(arriveCity);
        }
        return new PageBean<>(count, routes);
    }

    @Override
    public boolean addRouter(RouteInfoManager routeInfoManager) throws ParseException {
//        设置信息
        Timetable startTime = new Timetable();
        Timetable arriveTime = new Timetable();
        startTime.setType(0);
        arriveTime.setType(1);
        startTime.setScheduled(routeInfoManager.getStartTime().getScheduled());
        arriveTime.setScheduled(routeInfoManager.getArriveTime().getScheduled());
        Route route = new Route();
        route.setAircraftCode(routeInfoManager.getAircraftCode());
        route.setStartAirportCode(routeInfoManager.getStartAirportCode());
        route.setArriveAirportCode(routeInfoManager.getArriveAirportCode());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        route.setRouteCode(simpleDateFormat1.format(simpleDateFormat.parse(startTime.getScheduled())) + "+" + route.getAircraftCode());
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            timetableMapper.insert(startTime);
            timetableMapper.insert(arriveTime);
            route.setStartTimeId(startTime.getId());
            route.setArriveTimeId(arriveTime.getId());
            routeMapper.insert(route);
        } catch (Exception e) {
            txManager.rollback(status);
            e.printStackTrace();
            return false;
        }
        txManager.commit(status);
        return true;
    }

    @Override
    public boolean updateRouter(RouteInfoManager routeInfoManager) throws ParseException {
//      获取
        Timetable startTime = routeInfoManager.getStartTime();
        Timetable arriveTime = routeInfoManager.getArriveTime();
        Route route = new Route(routeInfoManager.getId(), routeInfoManager.getAircraftCode(), routeInfoManager.getStartAirportCode(), routeInfoManager.getArriveAirportCode(), routeInfoManager.getArriveTimeId(), routeInfoManager.getStartTimeId(), routeInfoManager.getRouteCode(), routeInfoManager.getStatus());

        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            timetableMapper.updateById(startTime);
            timetableMapper.updateById(arriveTime);
            routeMapper.updateById(route);
        }catch (Exception e){
            txManager.rollback(status);
            e.printStackTrace();
            return false;
        }
        txManager.commit(status);
        return true;
    }

    @Override
    public RouteInfoManager getRouterInfoById(String id) {
        RouteInfoManager router = routeMapper.selectRouterInfoManagerById(id);
        router.setStartCity(cityMapper.selectByIataApCode(router.getStartAirportCode()));
        router.setArriveCity(cityMapper.selectByIataApCode(router.getArriveAirportCode()));
        router.setStartTime(timetableMapper.selectById(router.getStartTimeId()));
        router.setArriveTime(timetableMapper.selectById(router.getArriveTimeId()));
        return router;
    }

    @Override
    public void deleteRouterById(String id) {
        routeMapper.deleteById(id);
    }
}
