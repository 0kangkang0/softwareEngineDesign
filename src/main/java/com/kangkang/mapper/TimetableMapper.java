package com.kangkang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangkang.pojo.Timetable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimetableMapper extends BaseMapper<Timetable> {
    void insertAll(Timetable arrive);
}
