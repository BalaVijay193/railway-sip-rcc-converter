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

    public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public SignalType getSignalType() {
		return signalType;
	}

	public void setSignalType(SignalType signalType) {
		this.signalType = signalType;
	}

	public double getxLeft() {
		return xLeft;
	}

	public void setxLeft(double xLeft) {
		this.xLeft = xLeft;
	}

	public double getyLeft() {
		return yLeft;
	}

	public void setyLeft(double yLeft) {
		this.yLeft = yLeft;
	}

	public double getxRight() {
		return xRight;
	}

	public void setxRight(double xRight) {
		this.xRight = xRight;
	}

	public double getyRight() {
		return yRight;
	}

	public void setyRight(double yRight) {
		this.yRight = yRight;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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
        STOP,
        STARTER,
        ADVANCED_STARTER,
        DEPENDENT_SHUNT,
        INDEPENDENT_SHUNT,

    }
}
