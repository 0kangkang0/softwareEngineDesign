package com.kangkang.service;

import com.kangkang.pojo.CityInfo;
import com.kangkang.pojo.PageBean;
import com.kangkang.pojo.SuperSelect;

import java.util.List;

public interface CityService {
    void add(CityInfo cityInfo);

    CityInfo selectByIataApCode(String idtaApCode);

    List<String> selectAllIataApCode();

    List<SuperSelect> selectAll();

    PageBean<CityInfo> selectAllInfo(CityInfo cityInfo, Integer currentPage, Integer pageSize);

    CityInfo getCityById(String id);

    void update(CityInfo cityInfo);

    void delete(String id);
}
