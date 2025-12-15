package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetailDto {
    private String routeId;
    private String routeName;
    private String entrySignal;
    private String exitSignal;
    private List<String> tracks;
    private List<String> signals;
    private List<String> points;
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
	public List<String> getTracks() {
		return tracks;
	}
	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}
	public List<String> getSignals() {
		return signals;
	}
	public void setSignals(List<String> signals) {
		this.signals = signals;
	}
	public List<String> getPoints() {
		return points;
	}
	public void setPoints(List<String> points) {
		this.points = points;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
