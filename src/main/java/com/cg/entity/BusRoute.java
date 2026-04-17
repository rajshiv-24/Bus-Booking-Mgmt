package com.cg.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "bus_route")
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String src;
    private String dest;

    @JsonIgnore
    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL)
    private List<RouteSchedule> schedules;

    public BusRoute() {}

    public BusRoute(Long id, String src, String dest, List<RouteSchedule> schedules) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.schedules = schedules;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public String getDest() { return dest; }
    public void setDest(String dest) { this.dest = dest; }

    public List<RouteSchedule> getSchedules() { return schedules; }
    public void setSchedules(List<RouteSchedule> schedules) { this.schedules = schedules; }
}