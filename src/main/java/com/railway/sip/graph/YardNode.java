package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardNode {
    private String nodeId;
    private String name;
    private NodeType type;
    private Direction direction;
    private SignalType signalType;
    private double xLeft;
    private double yLeft;
    private double xRight;
    private double yRight;
    private boolean visited;
    private int priority;
    private String state; // For points: NORMAL, REVERSE

    public enum NodeType {
        SIGNAL,
        TRACK,
        POINT,
        LEVEL_CROSSING,
        CRANK_HANDLE,
        CONNECTION
    }

    public enum Direction {
        UP,
        DOWN,
        BOTH
    }

    public enum SignalType {
        HOME,
        CALLING_ON,
        SHUNT,
        STOP
    }
}
