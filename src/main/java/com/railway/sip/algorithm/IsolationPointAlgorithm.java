package com.railway.sip.algorithm;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.graph.YardNode;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * Algorithm 4: Isolation Point Detection
 * Prevents parked rakes from rolling into route
 */
@Slf4j
public class IsolationPointAlgorithm {
    
    public static void detectIsolationPoints(Route route, YardGraph graph) {
        if (route == null || route.getControlTrackIds() == null) {
            return;
        }
        
        List<String> isolationPoints = new ArrayList<>();
        Set<String> routePoints = new HashSet<>(route.getPointPositions().keySet());
        
        // Find half-tracks in route
        Set<String> halfTracks = findHalfTracks(route, graph);
        
        // Search for isolation points near half-tracks
        for (String halfTrack : halfTracks) {
            YardNode halfTrackNode = graph.getNodes().get(halfTrack);
            
            // Look for points in reverse direction that control this half-track
            for (String pointId : routePoints) {
                YardNode pointNode = graph.getNodes().get(pointId);
                
                // Check if point can prevent rake from rolling into half-track
                if (canIsolate(pointNode, halfTrackNode, graph)) {
                    isolationPoints.add(pointId);
                    
                    // Set point to NORMAL position for isolation
                    route.getIsolationPointPositions().put(pointId, "NORMAL");
                }
            }
        }
        
        route.setIsolationPointIds(isolationPoints);
    }
    
    private static Set<String> findHalfTracks(Route route, YardGraph graph) {
        Set<String> halfTracks = new HashSet<>();
        
        // Identify half-tracks based on route geometry
        for (String trackId : route.getControlTrackIds()) {
            YardNode trackNode = graph.getNodes().get(trackId);
            
            // Check if track has exposed ends (half-tracks)
            boolean isHalfTrack = true;
            for (YardEdge edge : graph.getOutgoingEdges(trackId)) {
                if (route.getControlTrackIds().contains(edge.getTargetNodeId())) {
                    isHalfTrack = false;
                    break;
                }
            }
            
            if (isHalfTrack) {
                halfTracks.add(trackId);
            }
        }
        
        return halfTracks;
    }
    
    private static boolean canIsolate(YardNode point, YardNode halfTrack, YardGraph graph) {
        // Check if point can control access to half-track
        return point.getType() == YardNode.NodeType.POINT && 
               isReachable(point, halfTrack, graph);
    }
    
    private static boolean isReachable(YardNode from, YardNode to, YardGraph graph) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(from.getNodeId());
        visited.add(from.getNodeId());
        
        while (!queue.isEmpty()) {
            String nodeId = queue.poll();
            
            if (nodeId.equals(to.getNodeId())) {
                return true;
            }
            
            for (YardEdge edge : graph.getOutgoingEdges(nodeId)) {
                if (!visited.contains(edge.getTargetNodeId())) {
                    visited.add(edge.getTargetNodeId());
                    queue.add(edge.getTargetNodeId());
                }
            }
        }
        
        return false;
    }
}
