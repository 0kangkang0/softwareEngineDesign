package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.Route;
import com.kangkang.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//    1:已订票；2:已取消；3:已检票；4:已值机
@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {
    @Select("select * from software_engine_design.ticket where route_id=#{routeId}")
    List<Ticket>selectByRouteId(Long routeId);
    @Update("update software_engine_design.orders set status=2 where id=#{routeId}")
    void logicDeleteById(Long routeId);
    List<Ticket>selectAllTicketByRouterId(String id);
}
