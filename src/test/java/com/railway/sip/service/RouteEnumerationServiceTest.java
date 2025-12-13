package com.railway.sip.service;

import com.railway.sip.graph.*;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RouteEnumerationServiceTest {
    
    private RouteEnumerationService service;
    private YardGraph graph;
    
    @BeforeEach
    public void setUp() {
        service = new RouteEnumerationService();
        
        // Create test yard
        graph = YardGraph.builder()
            .yardId("TEST_YARD")
            .yardName("Test Yard")
            .build();
        
        // Add test signals and tracks
        YardNode signal1 = YardNode.builder()
            .nodeId("S1")
            .type(YardNode.NodeType.SIGNAL)
            .signalType(YardNode.SignalType.HOME)
            .direction(YardNode.Direction.DOWN)
            .build();
        
        YardNode signal2 = YardNode.builder()
            .nodeId("S2")
            .type(YardNode.NodeType.SIGNAL)
            .signalType(YardNode.SignalType.STARTER)
            .direction(YardNode.Direction.DOWN)
            .build();
        
        YardNode track = YardNode.builder()
            .nodeId("T1")
            .type(YardNode.NodeType.TRACK)
            .direction(YardNode.Direction.DOWN)
            .build();
        
        graph.addNode(signal1);
        graph.addNode(signal2);
        graph.addNode(track);
        
        graph.addEdge(YardEdge.builder()
            .sourceNodeId("S1")
            .targetNodeId("T1")
            .direction(YardNode.Direction.DOWN)
            .build());
        
        graph.addEdge(YardEdge.builder()
            .sourceNodeId("T1")
            .targetNodeId("S2")
            .direction(YardNode.Direction.DOWN)
            .build());
    }
    
    @Test
    public void testRouteEnumeration() throws ExecutionException, InterruptedException {
        Set<Route> routes = service.enumerateAllRoutes(graph);
        
        assertNotNull(routes);
        assertFalse(routes.isEmpty());
        log.info("Enumerated {} routes", routes.size());
    }
    
    @Test
    public void testRouteRetrieval() throws ExecutionException, InterruptedException {
        service.enumerateAllRoutes(graph);
        Set<Route> cachedRoutes = service.getRoutes("TEST_YARD");
        
        assertFalse(cachedRoutes.isEmpty());
        log.info("Retrieved {} cached routes", cachedRoutes.size());
    }
}
