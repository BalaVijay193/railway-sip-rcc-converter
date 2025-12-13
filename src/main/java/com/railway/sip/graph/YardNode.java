package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

/**
 * Represents a node in the yard graph
 * Types: TRACK, SIGNAL, POINT, LEVEL_CROSSING, STOP_BOARD, SAND_HUMP, HAND_PLUNGER_LOCK
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardNode {
    private String nodeId;
    private NodeType type;
    private double xLeft, yLeft, xRight, yRight;
    private String name;
    
    // Track properties
    private String trackCircuitId;
    private Direction direction;
    
    // Signal properties
    private int aspectCount;
    private SignalType signalType;
    private SignalFunction function;
    
    // Point properties
    private PointState pointState;
    private String startTrackId;
    private String endTrackId;
    private String crankHandleId;
    
    // Visited tracking
    private boolean visited;
    private Set<String> adjacentNodeIds = new HashSet<>();
    
    // Level crossing
    private String levelCrossingGateId;
    
    public enum NodeType {
        TRACK, SIGNAL, POINT, LEVEL_CROSSING, STOP_BOARD, 
        SAND_HUMP, HAND_PLUNGER_LOCK, DERAILMENT_POINT, TRAP_POINT, 
        AXLE_COUNTER, DEAD_END
    }
    
    public enum Direction {
        UP, DOWN, BOTH
    }
    
    public enum SignalType {
        HOME, STARTER, INTERMEDIATE_BLOCK, DEPENDENT_SHUNT, 
        INDEPENDENT_SHUNT, CALLING_ON, ADVANCED_STARTER
    }
    
    public enum SignalFunction {
        MAIN, SHUNT, CALLING_ON, COMBINATION
    }
    
    public enum PointState {
        NORMAL, REVERSE, UNKNOWN
    }
}
