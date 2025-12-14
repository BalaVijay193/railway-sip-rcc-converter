package com.railway.sip.model;

import com.railway.sip.graph.YardNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String routeId;
    private String routeName;
    private String entrySignalId;
    private String exitSignalId;
    private YardNode.Direction direction;
    private List<String> controlTrackIds;
    private Map<String, String> pointPositions;
    private List<String> overlapTrackIds;
    private List<String> isolationPointIds;
    private Map<String, String> isolationPointPositions;
    private List<String> crankHandleIds;
    private List<String> levelCrossingIds;
    private RouteType type;
    private String status;
    
    public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getEntrySignalId() {
		return entrySignalId;
	}

	public void setEntrySignalId(String entrySignalId) {
		this.entrySignalId = entrySignalId;
	}

	public String getExitSignalId() {
		return exitSignalId;
	}

	public void setExitSignalId(String exitSignalId) {
		this.exitSignalId = exitSignalId;
	}

	public YardNode.Direction getDirection() {
		return direction;
	}

	public void setDirection(YardNode.Direction direction) {
		this.direction = direction;
	}

	public List<String> getControlTrackIds() {
		return controlTrackIds;
	}

	public void setControlTrackIds(List<String> controlTrackIds) {
		this.controlTrackIds = controlTrackIds;
	}

	public Map<String, String> getPointPositions() {
		return pointPositions;
	}

	public void setPointPositions(Map<String, String> pointPositions) {
		this.pointPositions = pointPositions;
	}

	public List<String> getOverlapTrackIds() {
		return overlapTrackIds;
	}

	public void setOverlapTrackIds(List<String> overlapTrackIds) {
		this.overlapTrackIds = overlapTrackIds;
	}

	public List<String> getIsolationPointIds() {
		return isolationPointIds;
	}

	public void setIsolationPointIds(List<String> isolationPointIds) {
		this.isolationPointIds = isolationPointIds;
	}

	public Map<String, String> getIsolationPointPositions() {
		return isolationPointPositions;
	}

	public void setIsolationPointPositions(Map<String, String> isolationPointPositions) {
		this.isolationPointPositions = isolationPointPositions;
	}

	public List<String> getCrankHandleIds() {
		return crankHandleIds;
	}

	public void setCrankHandleIds(List<String> crankHandleIds) {
		this.crankHandleIds = crankHandleIds;
	}

	public List<String> getLevelCrossingIds() {
		return levelCrossingIds;
	}

	public void setLevelCrossingIds(List<String> levelCrossingIds) {
		this.levelCrossingIds = levelCrossingIds;
	}

	public RouteType getType() {
		return type;
	}

	public void setType(RouteType type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setApproachLockedTrack(String approachLockedTrack) {
		this.approachLockedTrack = approachLockedTrack;
	}

	public void setBackLockedTracks(List<String> backLockedTracks) {
		this.backLockedTracks = backLockedTracks;
	}


	// ADD THESE FIELDS
    private String approachLockedTrack;
    private List<String> backLockedTracks;

    // ADD THESE METHODS
    public String getApproachLockedTrack() {
        return this.approachLockedTrack;
    }

    public List<String> getBackLockedTracks() {
        return this.backLockedTracks;
    }


    public enum RouteType {
        MAINLINE,
        CALLING_ON,
        SHUNT,
        SIDING
    }
}
