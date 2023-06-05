package com.kangkang.service;

import com.kangkang.pojo.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public interface RouteService {
    void insert(Route route);
    List<Route> select(Route route);
    List<RouterInfo> selectInfo(QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException;
    PageBean<RouteInfoManager> getRouteInfo(QueryTicketInfo queryTicketInfo, Integer currentPage, Integer pageSize);
    boolean addRouter(RouteInfoManager routeInfoManager) throws ParseException;
    boolean updateRouter(RouteInfoManager routeInfoManager) throws ParseException;
    RouteInfoManager getRouterInfoById(String id);
    void deleteRouterById(String id);
}
