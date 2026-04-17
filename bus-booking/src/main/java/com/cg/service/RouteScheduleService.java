package com.cg.service;

import com.cg.entity.RouteSchedule;
import com.cg.repository.RouteScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteScheduleService {

    @Autowired
    private RouteScheduleRepository scheduleRepository;

    public List<RouteSchedule> getSchedules(String src, String dest) {
        return scheduleRepository.findBySourceAndDestination(src, dest);
    }
}