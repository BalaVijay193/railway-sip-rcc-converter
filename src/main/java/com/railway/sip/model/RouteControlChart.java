package com.railway.sip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

/**
 * Route Control Chart - tabular representation of all yard routes
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteControlChart {
    private String rccId;
    private String yardId;
    private String yardName;
    private List<RouteEntry> entries = new ArrayList<>();
    private long createdAt = System.currentTimeMillis();
    private String generatedBy;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteEntry {
        private String routeName;
        private String entrySignal;
        private String exitSignal;
        private String routeButton;
        
        private Set<String> controlTracks;
        private Set<String> pointNormal;
        private Set<String> pointReverse;
        
        private Set<String> overlapTracks;
        private Set<String> overlapPointNormal;
        private Set<String> overlapPointReverse;
        private Set<String> overlapClearTracks;
        private Set<String> overlapOccupiedTracks;
        
        private Set<String> isolationPointNormal;
        private Set<String> isolationPointReverse;
        
        private Set<String> crankHandles;
        private Set<String> levelCrossings;
        
        private String approachLocked;
        private Set<String> backLocked;
        
        private Set<String> conflictingRoutes;
        
        private String routeType;
        private int sequenceNumber;
    }
    
    public void addRouteEntry(RouteEntry entry) {
        entries.add(entry);
    }
    
    public RouteEntry getRouteEntry(String routeName) {
        return entries.stream()
            .filter(e -> e.getRouteName().equals(routeName))
            .findFirst()
            .orElse(null);
    }
    
    public int getTotalRoutes() {
        return entries.size();
    }
    
    
}
