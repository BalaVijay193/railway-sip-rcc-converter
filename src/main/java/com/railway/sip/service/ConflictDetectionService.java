package com.railway.sip.service;

import com.railway.sip.algorithm.ConflictResolutionAlgorithm;
import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class ConflictDetectionService {
    
    public Map<String, Set<RouteConflict>> detectAllConflicts(Set<Route> routes, YardGraph graph) {
        Map<String, Set<RouteConflict>> conflicts = new HashMap<>();
        
        List<Route> routeList = new ArrayList<>(routes);
        
        for (int i = 0; i < routeList.size(); i++) {
            for (int j = i + 1; j < routeList.size(); j++) {
                Route r1 = routeList.get(i);
                Route r2 = routeList.get(j);
                
                RouteConflict conflict = detectConflict(r1, r2, graph);
                if (conflict != null) {
                    conflicts.computeIfAbsent(r1.getRouteId(), k -> new HashSet<>()).add(conflict);
                    conflicts.computeIfAbsent(r2.getRouteId(), k -> new HashSet<>()).add(conflict);
                }
            }
        }
        
        return conflicts;
    }
    
    private RouteConflict detectConflict(Route r1, Route r2, YardGraph graph) {
        // Check common tracks
        Set<String> commonTracks = new HashSet<>(r1.getControlTrackIds());
        commonTracks.retainAll(r2.getControlTrackIds());
        
        if (!commonTracks.isEmpty()) {
            return RouteConflict.builder()
                .route1Id(r1.getRouteId())
                .route2Id(r2.getRouteId())
                .type(ConflictResolutionAlgorithm.ConflictType.COMMON_TRACK)
                .severity("HIGH")
                .description("Routes share control tracks")
                .build();
        }
        
        return null;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteConflict {
        private String route1Id;
        private String route2Id;
        private ConflictResolutionAlgorithm.ConflictType type;
        private String severity;
        private String description;
    }
}
