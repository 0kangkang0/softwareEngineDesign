package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.OrderManager;
import com.kangkang.pojo.Orders;
import com.kangkang.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
    @Select("select * from software_engine_design.orders where user_id=#{userId}")
    List<Orders> selectByUserId(Long userId);

    List<OrderManager> selectAllOrderManager(@Param("orders") Orders orders, @Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectAllOrderManagerCount(@Param("orders") Orders orders);
}
