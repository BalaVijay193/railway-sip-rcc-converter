package com.railway.sip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteControlChart {
    private String rccId;
    private String yardId;
    private String routeId;
    private List<String> controlPoints;
    private Map<String, String> pointSettings;
    private List<String> levelCrossings;
    private List<String> crankHandles;
    private String createdDate;
    private String lastModifiedDate;
    private String yardName;
    private int totalRoutes;
    private LocalDateTime createdAt;
    @Builder.Default
    private List<RouteEntry> entries = new ArrayList<>();
    public void addRouteEntry(RouteEntry entry) { this.entries.add(entry); }
    public List<RouteEntry> getEntries() { return this.entries; }
    public int getTotalRoutes() { return this.totalRoutes; }
    public String getYardName() { return this.yardName; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
  
    public String getRccId() {
		return rccId;
	}
	public void setRccId(String rccId) {
		this.rccId = rccId;
	}
	public String getYardId() {
		return yardId;
	}
	public void setYardId(String yardId) {
		this.yardId = yardId;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public List<String> getControlPoints() {
		return controlPoints;
	}
	public void setControlPoints(List<String> controlPoints) {
		this.controlPoints = controlPoints;
	}
	public Map<String, String> getPointSettings() {
		return pointSettings;
	}
	public void setPointSettings(Map<String, String> pointSettings) {
		this.pointSettings = pointSettings;
	}
	public List<String> getLevelCrossings() {
		return levelCrossings;
	}
	public void setLevelCrossings(List<String> levelCrossings) {
		this.levelCrossings = levelCrossings;
	}
	public List<String> getCrankHandles() {
		return crankHandles;
	}
	public void setCrankHandles(List<String> crankHandles) {
		this.crankHandles = crankHandles;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public void setYardName(String yardName) {
		this.yardName = yardName;
	}
	public void setTotalRoutes(int totalRoutes) {
		this.totalRoutes = totalRoutes;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setEntries(List<RouteEntry> entries) {
		this.entries = entries;
	}

	@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteEntry {
        private String routeId;
        private String routeName;
        private String entrySignalId;                          // Keep
        private String exitSignalId;                           // Keep
        @Builder.Default
        private List<String> controlTracks = new ArrayList<>();   // Keep
        @Builder.Default
        private List<String> overlapTracks = new ArrayList<>();   // Keep
        
        // ADD THESE:
        private Set<String> conflictingRoutes;                 // ADD THIS
        
        // ADD THIS METHOD
        public void setConflictingRoutes(Set<String> routes) {
            this.conflictingRoutes = routes;
        }
    }
}
