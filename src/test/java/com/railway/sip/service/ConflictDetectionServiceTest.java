package com.railway.sip.service;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ConflictDetectionServiceTest {
    
    private ConflictDetectionService service;
    private YardGraph graph;
    private Set<Route> routes;
    
    @BeforeEach
    public void setUp() {
        service = new ConflictDetectionService();
        graph = YardGraph.builder().yardId("TEST").build();
        
        Route r1 = Route.builder()
            .routeId("R1")
            .controlTrackIds(Arrays.asList("T1", "T2"))
            .build();
        
        Route r2 = Route.builder()
            .routeId("R2")
            .controlTrackIds(Arrays.asList("T2", "T3"))
            .build();
        
        routes = new HashSet<>(Arrays.asList(r1, r2));
    }
    
    @Test
    public void testConflictDetection() {
        Map<String, Set<ConflictDetectionService.RouteConflict>> conflicts = 
            service.detectAllConflicts(routes, graph);
        
        assertFalse(conflicts.isEmpty());
        log.info("Detected {} route conflicts", conflicts.size());
    }
}
