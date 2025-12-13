package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a directed edge in the yard graph
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardEdge {
    private String sourceNodeId;
    private String targetNodeId;
    private YardNode.Direction direction;
    private double weight;
    private EdgeType edgeType;
    
    public enum EdgeType {
        TRACK_TO_TRACK,
        TRACK_TO_POINT,
        POINT_TO_TRACK,
        TRACK_TO_SIGNAL,
        SIGNAL_TO_TRACK,
        TRACK_TO_LEVEL_CROSSING,
        LEVEL_CROSSING_TO_TRACK
    }
}
