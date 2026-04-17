package com.cg.repository;

import com.cg.entity.RouteSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RouteScheduleRepository extends JpaRepository<RouteSchedule, Long> {

    @Query("SELECT rs FROM RouteSchedule rs WHERE rs.busRoute.src = :src AND rs.busRoute.dest = :dest AND rs.schStatus = 'ACTIVE'")
    List<RouteSchedule> findBySourceAndDestination(String src, String dest);
}