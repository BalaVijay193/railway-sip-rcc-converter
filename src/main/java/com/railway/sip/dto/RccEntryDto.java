package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public 
class RccEntryDto {
    private String routeName;
    private String entrySignal;
    private String exitSignal;
    private Set<String> controlTracks;
    private Set<String> pointNormal;
    private Set<String> pointReverse;
    private Set<String> conflictingRoutes;
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
	public Set<String> getControlTracks() {
		return controlTracks;
	}
	public void setControlTracks(Set<String> controlTracks) {
		this.controlTracks = controlTracks;
	}
	public Set<String> getPointNormal() {
		return pointNormal;
	}
	public void setPointNormal(Set<String> pointNormal) {
		this.pointNormal = pointNormal;
	}
	public Set<String> getPointReverse() {
		return pointReverse;
	}
	public void setPointReverse(Set<String> pointReverse) {
		this.pointReverse = pointReverse;
	}
	public Set<String> getConflictingRoutes() {
		return conflictingRoutes;
	}
	public void setConflictingRoutes(Set<String> conflictingRoutes) {
		this.conflictingRoutes = conflictingRoutes;
	}
    
}
