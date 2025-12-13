package com.railway.sip.service;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import com.railway.sip.model.RouteControlChart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RccGenerationService {
    
    @Autowired
    private ConflictDetectionService conflictDetectionService;
    
    private final Map<String, RouteControlChart> rccCache = new HashMap<>();
    
    /**
     * Generate complete RCC from selected routes
     */
    public RouteControlChart generateRcc(String rccId, String yardId, String yardName,
                                         Set<Route> selectedRoutes, YardGraph graph) {
        long startTime = System.currentTimeMillis();
        
        RouteControlChart rcc = RouteControlChart.builder()
            .rccId(rccId)
            .yardId(yardId)
            .yardName(yardName)
            .generatedBy("RouteEnumerationAlgorithm v1.0")
            .build();
        
        // Detect conflicts first
        Map<String, Set<ConflictDetectionService.RouteConflict>> allConflicts = 
            conflictDetectionService.detectAllConflicts(selectedRoutes, graph);
        
        // Generate entry for each route
        int sequence = 1;
        for (Route route : selectedRoutes) {
            RouteControlChart.RouteEntry entry = generateRouteEntry(route, sequence, allConflicts);
            rcc.addRouteEntry(entry);
            sequence++;
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("RCC generation completed in {}ms: {} routes, {} entries", 
                 duration, selectedRoutes.size(), rcc.getTotalRoutes());
        
        rccCache.put(rccId, rcc);
        return rcc;
    }
    
    /**
     * Generate single RCC entry (table row)
     */
    private RouteControlChart.RouteEntry generateRouteEntry(Route route, int sequence,
                                                            Map<String, Set<ConflictDetectionService.RouteConflict>> allConflicts) {
        RouteControlChart.RouteEntry entry = RouteControlChart.RouteEntry.builder()
            .routeName(route.getRouteName())
            .entrySignal(route.getEntrySignalId())
            .exitSignal(route.getExitSignalId())
            .controlTracks(new HashSet<>(route.getControlTrackIds()))
            .overlapTracks(new HashSet<>(route.getOverlapTrackIds()))
            .pointNormal(extractPointsByPosition(route, "NORMAL"))
            .pointReverse(extractPointsByPosition(route, "REVERSE"))
            .overlapPointNormal(new HashSet<>())
            .overlapPointReverse(new HashSet<>())
            .isolationPointNormal(new HashSet<>())
            .isolationPointReverse(new HashSet<>())
            .crankHandles(new HashSet<>(route.getCrankHandleIds() != null ? route.getCrankHandleIds() : new ArrayList<>()))
            .levelCrossings(new HashSet<>(route.getLevelCrossingIds() != null ? route.getLevelCrossingIds() : new ArrayList<>()))
            .approachLocked(route.getApproachLockedTrack())
            .backLocked(new HashSet<>(route.getBackLockedTracks() != null ? route.getBackLockedTracks() : new ArrayList<>()))
            .routeType(route.getType().name())
            .sequenceNumber(sequence)
            .build();
        
        // Add conflicting routes
        Set<ConflictDetectionService.RouteConflict> routeConflicts = allConflicts.get(route.getRouteId());
        if (routeConflicts != null) {
            Set<String> conflictingRoutes = routeConflicts.stream()
                .map(c -> c.getRoute1Id().equals(route.getRouteId()) ? c.getRoute2Id() : c.getRoute1Id())
                .collect(Collectors.toSet());
            entry.setConflictingRoutes(conflictingRoutes);
        } else {
            entry.setConflictingRoutes(new HashSet<>());
        }
        
        return entry;
    }
    
    private Set<String> extractPointsByPosition(Route route, String position) {
        return route.getPointPositions().entrySet().stream()
            .filter(e -> e.getValue().equals(position))
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }
    
    public RouteControlChart getRcc(String rccId) {
        return rccCache.get(rccId);
    }
    
    public Collection<RouteControlChart> getAllRccs() {
        return rccCache.values();
    }
}
