package com.cg.repository;

import com.cg.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
    List<BusRoute> findBySrcIgnoreCaseAndDestIgnoreCase(String src, String dest);
}