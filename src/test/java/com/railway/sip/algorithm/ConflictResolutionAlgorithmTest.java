package com.railway.sip.algorithm;

import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ConflictResolutionAlgorithmTest {
    
    private Route route1;
    private Route route2;
    private Route route3;
    
    @BeforeEach
    public void setUp() {
        // Route 1: S2 to S4
        route1 = Route.builder()
            .routeId("R1")
            .routeName("1A")
            .entrySignalId("S2")
            .exitSignalId("S4")
            .type(Route.RouteType.MAINLINE)
            .controlTrackIds(Arrays.asList("1T", "1T2", "2T"))
            .pointPositions(new HashMap<>(Map.of("P21", "NORMAL")))
            .overlapTrackIds(Arrays.asList("3T"))
            .isolationPointIds(new ArrayList<>())
            .build();
        
        // Route 2: S3 to S5 (conflicts with Route 1)
        route2 = Route.builder()
            .routeId("R2")
            .routeName("2A")
            .entrySignalId("S3")
            .exitSignalId("S5")
            .type(Route.RouteType.MAINLINE)
            .controlTrackIds(Arrays.asList("1T", "2T2")) // Common track: 1T, 2T
            .pointPositions(new HashMap<>(Map.of("P21", "NORMAL")))
            .overlapTrackIds(Arrays.asList("4T"))
            .isolationPointIds(new ArrayList<>())
            .build();
        
        // Route 3: S4 to S6 (no conflict)
        route3 = Route.builder()
            .routeId("R3")
            .routeName("3A")
            .entrySignalId("S4")
            .exitSignalId("S6")
            .type(Route.RouteType.MAINLINE)
            .controlTrackIds(Arrays.asList("5T", "6T"))
            .pointPositions(new HashMap<>(Map.of("P31", "REVERSE")))
            .overlapTrackIds(Arrays.asList("7T"))
            .isolationPointIds(new ArrayList<>())
            .build();
    }
    
    @Test
    public void testConflictDetection() {
        ConflictResolutionAlgorithm.ConflictType conflict = 
            ConflictResolutionAlgorithm.detectConflict(route1, route2);
        
        assertEquals(ConflictResolutionAlgorithm.ConflictType.COMMON_TRACK, conflict);
        log.info("Detected conflict: {}", conflict);
    }
    
    @Test
    public void testNoConflict() {
        ConflictResolutionAlgorithm.ConflictType conflict = 
            ConflictResolutionAlgorithm.detectConflict(route1, route3);
        
        assertEquals(ConflictResolutionAlgorithm.ConflictType.NONE, conflict);
        log.info("No conflict detected between routes");
    }
    
    @Test
    public void testConflictResolution() {
        Set<Route> routes = new HashSet<>(Arrays.asList(route1, route2, route3));
        Map<Route, Set<Route>> conflictMap = ConflictResolutionAlgorithm.resolveConflicts(routes);
        
        assertFalse(conflictMap.isEmpty());
        assertTrue(conflictMap.containsKey(route1));
        log.info("Conflict resolution completed: {} routes have conflicts", conflictMap.size());
    }
}
