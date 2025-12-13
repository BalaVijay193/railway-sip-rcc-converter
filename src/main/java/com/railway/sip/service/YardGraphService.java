package com.railway.sip.service;

import com.railway.sip.graph.*;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class YardGraphService {
    private final Map<String, YardGraph> yardGraphCache = new HashMap<>();
    
    public YardGraph createYardGraph(String yardId, String yardName) {
        YardGraph graph = YardGraph.builder()
            .yardId(yardId)
            .yardName(yardName)
            .build();
        
        yardGraphCache.put(yardId, graph);
        log.info("Created yard graph: {}", yardId);
        return graph;
    }
    
    public void addYardComponent(String yardId, YardNode node) {
        YardGraph graph = yardGraphCache.get(yardId);
        if (graph == null) {
            throw new IllegalArgumentException("Yard not found: " + yardId);
        }
        graph.addNode(node);
    }
    
    public void connectComponents(String yardId, String sourceId, String targetId, 
                                 YardNode.Direction direction, double weight) {
        YardGraph graph = yardGraphCache.get(yardId);
        YardEdge edge = YardEdge.builder()
            .sourceNodeId(sourceId)
            .targetNodeId(targetId)
            .direction(direction)
            .weight(weight)
            .build();
        
        graph.addEdge(edge);
    }
    
    public YardGraph getYardGraph(String yardId) {
        return yardGraphCache.get(yardId);
    }
}
