package com.railway.sip.algorithm;

import com.railway.sip.graph.*;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RouteEnumerationAlgorithmTest {
    
    private YardGraph graph;
    
    @BeforeEach
    public void setUp() {
        // Create Markona test yard
        graph = YardGraph.builder()
            .yardId("MARKONA_001")
            .yardName("Markona Railway Yard")
            .build();
        
        // Add signals
        YardNode s13 = YardNode.builder()
            .nodeId("S13")
            .type(YardNode.NodeType.SIGNAL)
            .name("Signal 13")
            .signalType(YardNode.SignalType.HOME)
            .direction(YardNode.Direction.DOWN)
            .aspectCount(2)
            .xLeft(4350).yLeft(4520)
            .xRight(4370).yRight(4520)
            .build();
        
        YardNode s14 = YardNode.builder()
            .nodeId("S14")
            .type(YardNode.NodeType.SIGNAL)
            .name("Signal 14")
            .signalType(YardNode.SignalType.STARTER)
            .direction(YardNode.Direction.DOWN)
            .aspectCount(2)
            .xLeft(4400).yLeft(4520)
            .xRight(4420).yRight(4520)
            .build();
        
        // Add tracks
        YardNode t13at = YardNode.builder()
            .nodeId("13AT")
            .type(YardNode.NodeType.TRACK)
            .name("Track 13 Above")
            .trackCircuitId("TCP_13")
            .direction(YardNode.Direction.DOWN)
            .xLeft(4380).yLeft(4520)
            .xRight(4480).yRight(4520)
            .build();
        
        graph.addNode(s13);
        graph.addNode(s14);
        graph.addNode(t13at);
        
        // Add edges
        YardEdge e1 = YardEdge.builder()
            .sourceNodeId("S13")
            .targetNodeId("13AT")
            .direction(YardNode.Direction.DOWN)
            .weight(1.0)
            .build();
        
        YardEdge e2 = YardEdge.builder()
            .sourceNodeId("13AT")
            .targetNodeId("S14")
            .direction(YardNode.Direction.DOWN)
            .weight(1.0)
            .build();
        
        graph.addEdge(e1);
        graph.addEdge(e2);
    }
    
    @Test
    public void testRouteEnumeration() {
        Set<Route> routes = RouteEnumerationAlgorithm.enumerateRoutes(graph, YardNode.Direction.DOWN);
        
        assertNotNull(routes);
        assertFalse(routes.isEmpty());
        log.info("Enumerated {} routes", routes.size());
        
        routes.forEach(r -> log.info("Route: {} -> {}", r.getEntrySignalId(), r.getExitSignalId()));
    }
    
    @Test
    public void testRouteControlTracks() {
        Set<Route> routes = RouteEnumerationAlgorithm.enumerateRoutes(graph, YardNode.Direction.DOWN);
        
        Route testRoute = routes.stream().findFirst().orElse(null);
        assertNotNull(testRoute);
        assertFalse(testRoute.getControlTrackIds().isEmpty());
        assertTrue(testRoute.getControlTrackIds().contains("13AT"));
    }
}
