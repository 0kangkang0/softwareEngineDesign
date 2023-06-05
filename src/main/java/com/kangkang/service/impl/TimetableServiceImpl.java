package com.kangkang.service.impl;

import com.kangkang.mapper.TimetableMapper;
import com.kangkang.pojo.Timetable;
import com.kangkang.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    TimetableMapper timetableMapper;

    @Override
    public void insert(Timetable timetable) {
        timetableMapper.insertAll(timetable);
    }
}
