package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardEdge {
    private String edgeId;
    private String sourceNodeId;
    private String targetNodeId;
    private double weight;
    private YardNode.Direction direction;
    private String type;
    private boolean bidirectional;
	public String getEdgeId() {
		return edgeId;
	}
	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	}
	public String getSourceNodeId() {
		return sourceNodeId;
	}
	public void setSourceNodeId(String sourceNodeId) {
		this.sourceNodeId = sourceNodeId;
	}
	public String getTargetNodeId() {
		return targetNodeId;
	}
	public void setTargetNodeId(String targetNodeId) {
		this.targetNodeId = targetNodeId;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public YardNode.Direction getDirection() {
		return direction;
	}
	public void setDirection(YardNode.Direction direction) {
		this.direction = direction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isBidirectional() {
		return bidirectional;
	}
	public void setBidirectional(boolean bidirectional) {
		this.bidirectional = bidirectional;
	}
    
    
}
