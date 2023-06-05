package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.Route;
import com.kangkang.pojo.RouteInfoManager;
import com.kangkang.pojo.TicketManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RouteMapper extends BaseMapper<Route> {
    List<Route> selectList(Route route);

    List<Route> selectByTimeStartEnd(String start, String end, String startTime, String endTime);

    List<RouteInfoManager> selectPageByInfo(@Param("start") String start, @Param("end") String end, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("begin") Integer begin, @Param("size") Integer size);

    RouteInfoManager selectRouterInfoManagerById(String id);

    List<TicketManager> selectAllTicketManager();

    Integer selectCountByInfo(@Param("start") String start, @Param("end") String end, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<TicketManager> selectTicketManagerPageByInfo(@Param("start") String start, @Param("end") String end, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("begin") Integer begin, @Param("size") Integer size);

    TicketManager selectTicketManagerById(String id);
}
