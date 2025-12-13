package com.railway.sip.algorithm;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.graph.YardNode;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * Algorithm 2-3: Overlap enumeration
 * Identifies overlap tracks beyond route exit signal
 */
@Slf4j
public class OverlapEnumerationAlgorithm {
    
    private static final int DEFAULT_OVERLAP_LENGTH = 50;
    
    public static void enumerateOverlaps(Route route, YardGraph graph) {
        if (route == null || route.getExitSignalId() == null) {
            return;
        }
        
        YardNode exitSignal = graph.getNodes().get(route.getExitSignalId());
        if (exitSignal == null) {
            return;
        }
        
        List<String> overlapTracks = new ArrayList<>();
        
        // Traverse beyond exit signal for overlap distance
        Queue<TraversalState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(new TraversalState(exitSignal, 0));
        visited.add(exitSignal.getNodeId());
        
        while (!queue.isEmpty()) {
            TraversalState state = queue.poll();
            
            // Stop if we've traveled beyond overlap length
            if (state.distance > DEFAULT_OVERLAP_LENGTH) {
                break;
            }
            
            // Add successor nodes
            for (YardEdge edge : graph.getOutgoingEdges(state.node.getNodeId())) {
                if (state.node.getDirection() == route.getDirection() || 
                    state.node.getDirection() == YardNode.Direction.BOTH) {
                    
                    YardNode nextNode = graph.getNodes().get(edge.getTargetNodeId());
                    if (!visited.contains(nextNode.getNodeId())) {
                        visited.add(nextNode.getNodeId());
                        
                        if (nextNode.getType() == YardNode.NodeType.TRACK) {
                            overlapTracks.add(nextNode.getNodeId());
                        }
                        
                        queue.add(new TraversalState(nextNode, state.distance + (int)edge.getWeight()));
                    }
                }
            }
        }
        
        route.setOverlapTrackIds(overlapTracks);
    }
    
    static class TraversalState {
        YardNode node;
        int distance;
        
        TraversalState(YardNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
