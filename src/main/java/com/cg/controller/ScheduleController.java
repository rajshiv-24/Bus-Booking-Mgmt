package com.cg.controller;

import com.cg.dto.AdminScheduleRequest;
import com.cg.entity.RouteSchedule;
import com.cg.service.RouteScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {

    @Autowired
    private RouteScheduleService scheduleService;

    // ─── USER endpoints ────────────────────────────────────

    // Search schedules by src and dest
    @GetMapping("/api/schedules/search")
    public ResponseEntity<List<RouteSchedule>> getSchedules(
            @RequestParam String src,
            @RequestParam String dest) {
        return ResponseEntity.ok(scheduleService.getSchedules(src, dest));
    }

    // ─── ADMIN endpoints ───────────────────────────────────

    // View all schedules
    @GetMapping("/api/admin/schedules")
    public ResponseEntity<List<RouteSchedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    // Add new schedule
    @PostMapping("/api/admin/schedules")
    public ResponseEntity<?> addSchedule(@RequestBody AdminScheduleRequest request) {
        try {
            RouteSchedule saved = scheduleService.addSchedule(request);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update schedule
    @PutMapping("/api/admin/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id,
                                             @RequestBody AdminScheduleRequest request) {
        try {
            RouteSchedule updated = scheduleService.updateSchedule(id, request);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete schedule
    @DeleteMapping("/api/admin/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(scheduleService.deleteSchedule(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}