package com.railway.sip.algorithm;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * Algorithms 5-7: Conflict Detection and Resolution
 */
@Slf4j
public class ConflictResolutionAlgorithm {

    public enum ConflictType {
        COMMON_TRACK,
        OPPOSITE_DIRECTION,
        POINT_MISMATCH,
        ISOLATION_CONFLICT,
        NONE
    }

    public static ConflictType detectConflict(Route r1, Route r2) {
        // Check 1: Common control tracks
        Set<String> commonTracks = new HashSet<>(r1.getControlTrackIds());
        commonTracks.retainAll(r2.getControlTrackIds());
        if (!commonTracks.isEmpty()) {
            return ConflictType.COMMON_TRACK;
        }

        // Check 2: Opposite direction conflict
        if (!r1.getDirection().equals(r2.getDirection())) {
            Set<String> commonOverlap = new HashSet<>(r1.getOverlapTrackIds());
            commonOverlap.retainAll(r2.getOverlapTrackIds());
            if (!commonOverlap.isEmpty()) {
                return ConflictType.OPPOSITE_DIRECTION;
            }
        }

        // Check 3: Point position mismatch
        Set<String> commonPoints = new HashSet<>(r1.getPointPositions().keySet());
        commonPoints.retainAll(r2.getPointPositions().keySet());
        for (String pointId : commonPoints) {
            String pos1 = r1.getPointPositions().get(pointId);
            String pos2 = r2.getPointPositions().get(pointId);
            if (!pos1.equals(pos2)) {
                return ConflictType.POINT_MISMATCH;
            }
        }

        // Check 4: Isolation point conflicts
        Set<String> commonIsolation = new HashSet<>(r1.getIsolationPointIds());
        commonIsolation.retainAll(r2.getIsolationPointIds());
        if (!commonIsolation.isEmpty()) {
            return ConflictType.ISOLATION_CONFLICT;
        }

        return ConflictType.NONE;
    }

    public static Map<Route, Set<Route>> resolveConflicts(Set<Route> routes) {
        Map<Route, Set<Route>> conflictMap = new HashMap<>();
        List<Route> routeList = new ArrayList<>(routes);

        for (int i = 0; i < routeList.size(); i++) {
            for (int j = i + 1; j < routeList.size(); j++) {
                Route r1 = routeList.get(i);
                Route r2 = routeList.get(j);
                ConflictType conflict = detectConflict(r1, r2);

                if (conflict != ConflictType.NONE) {
                    conflictMap.computeIfAbsent(r1, k -> new HashSet<>()).add(r2);
                    conflictMap.computeIfAbsent(r2, k -> new HashSet<>()).add(r1);
                    log.info("Conflict detected: {} between {} and {}", conflict, r1.getRouteId(), r2.getRouteId());
                }
            }
        }

        return conflictMap;
    }
}
