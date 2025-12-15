package com.railway.sip.request;

import com.railway.sip.graph.YardNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteEnumerationRequest {
    private String yardId;
    private YardNode.Direction direction;
    private boolean includeConflicts;
    
    private String routeType;  // ADD THIS

    public String getYardId() {
		return yardId;
	}

	public void setYardId(String yardId) {
		this.yardId = yardId;
	}

	public YardNode.Direction getDirection() {
		return direction;
	}

	public void setDirection(YardNode.Direction direction) {
		this.direction = direction;
	}

	public boolean isIncludeConflicts() {
		return includeConflicts;
	}

	public void setIncludeConflicts(boolean includeConflicts) {
		this.includeConflicts = includeConflicts;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	// ADD THIS METHOD
    public String getRouteType() {
        return this.routeType;
    }

}
