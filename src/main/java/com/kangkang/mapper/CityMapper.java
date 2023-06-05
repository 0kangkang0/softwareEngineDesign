package com.kangkang.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.CityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper extends BaseMapper<CityInfo> {
    @Select("select * from software_engine_design.city_info where iata_ap_code=#{iataApCode}")
    CityInfo selectByIataApCode(String iataApCode);

    @Select("select iata_ap_code from software_engine_design.city_info")
    List<String> selectAllIataApCode();

    @Select("select * from software_engine_design.city_info")
    List<CityInfo> selectAll();
    List<CityInfo> selectPageByName(@Param("cityInfo") CityInfo cityInfo, @Param("begin") Integer begin,@Param("size") Integer size);
    Integer selectcountByName(@Param("cityInfo") CityInfo cityInfo);

}
