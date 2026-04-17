package com.cg.controller;

import com.cg.entity.RouteSchedule;
import com.cg.service.RouteScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {

    @Autowired
    private RouteScheduleService scheduleService;

    @GetMapping("/search")
    public ResponseEntity<List<RouteSchedule>> getSchedules(
            @RequestParam String src,
            @RequestParam String dest) {
        return ResponseEntity.ok(scheduleService.getSchedules(src, dest));
    }
}