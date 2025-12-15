package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private String routeId;
    private String routeName;
    private String entrySignal;
    private String exitSignal;
    private String type;
    private String direction;
    private int controlTrackCount;
    private int overlapTrackCount;
    private int pointCount;
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
	public String getEntrySignal() {
		return entrySignal;
	}
	public void setEntrySignal(String entrySignal) {
		this.entrySignal = entrySignal;
	}
	public String getExitSignal() {
		return exitSignal;
	}
	public void setExitSignal(String exitSignal) {
		this.exitSignal = exitSignal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getControlTrackCount() {
		return controlTrackCount;
	}
	public void setControlTrackCount(int controlTrackCount) {
		this.controlTrackCount = controlTrackCount;
	}
	public int getOverlapTrackCount() {
		return overlapTrackCount;
	}
	public void setOverlapTrackCount(int overlapTrackCount) {
		this.overlapTrackCount = overlapTrackCount;
	}
	public int getPointCount() {
		return pointCount;
	}
	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
	}
}
