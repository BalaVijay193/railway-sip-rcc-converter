package com.railway.sip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

/**
 * Represents a railway route from entry signal to exit signal
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String routeId;
    private String routeName;
    private String entrySignalId;
    private String exitSignalId;
    private RouteType type;
    private com.railway.sip.graph.YardNode.Direction direction;
    
    private List<String> controlTrackIds;
    private List<String> overlapTrackIds;
    private List<String> isolationPointIds;
    
    private Map<String, String> pointPositions;
    private Map<String, String> overlapPointPositions;
    private Map<String, String> isolationPointPositions;
    
    private List<String> crankHandleIds;
    private List<String> levelCrossingIds;
    
    private Set<String> conflictingRouteIds = new HashSet<>();
    
    private String approachLockedTrack;
    private List<String> backLockedTracks;
    
    private long createdAt = System.currentTimeMillis();
    
    public enum RouteType {
        MAINLINE, SHUNT, CALLING_ON, INDEPENDENT_SHUNT, DEPENDENT_SHUNT
    }
}
