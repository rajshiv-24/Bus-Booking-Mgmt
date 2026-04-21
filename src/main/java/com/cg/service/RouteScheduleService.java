package com.cg.service;

import com.cg.dto.AdminScheduleRequest;
import com.cg.entity.BusRoute;
import com.cg.entity.RouteSchedule;
import com.cg.repository.BusRouteRepository;
import com.cg.repository.RouteScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteScheduleService {

    @Autowired
    private RouteScheduleRepository scheduleRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;

    // ─── USER ──────────────────────────────────────────────
    // Search schedules by source and destination
    public List<RouteSchedule> getSchedules(String src, String dest) {
        return scheduleRepository.findBySourceAndDestination(src, dest);
    }

    // ─── ADMIN ─────────────────────────────────────────────
    // Get all schedules
    public List<RouteSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // Add new schedule
    public RouteSchedule addSchedule(AdminScheduleRequest request) {
        BusRoute route = busRouteRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + request.getRouteId()));

        RouteSchedule schedule = new RouteSchedule();
        schedule.setBusRoute(route);
        schedule.setDepartureTime(request.getDepartureTime());
        schedule.setScheduleDt(request.getScheduleDt());
        schedule.setAvlSeats(request.getAvlSeats());
        schedule.setTotSeats(request.getTotSeats());
        schedule.setSchStatus(request.getSchStatus());

        return scheduleRepository.save(schedule);
    }

    // Update existing schedule
    public RouteSchedule updateSchedule(Long id, AdminScheduleRequest request) {
        RouteSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        BusRoute route = busRouteRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + request.getRouteId()));

        schedule.setBusRoute(route);
        schedule.setDepartureTime(request.getDepartureTime());
        schedule.setScheduleDt(request.getScheduleDt());
        schedule.setAvlSeats(request.getAvlSeats());
        schedule.setTotSeats(request.getTotSeats());
        schedule.setSchStatus(request.getSchStatus());

        return scheduleRepository.save(schedule);
    }

    // Delete schedule
    public String deleteSchedule(Long id) {
        RouteSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.delete(schedule);
        return "Schedule deleted successfully";
    }
}