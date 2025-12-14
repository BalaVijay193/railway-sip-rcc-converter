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
