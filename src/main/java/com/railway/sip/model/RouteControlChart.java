package com.railway.sip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public static class RouteEntry {
    private String routeId;
    private String routeName;
    private String entrySignalId;
    private String exitSignalId;
    @Builder.Default
    private List<String> controlTracks = new ArrayList<>();
    @Builder.Default
    private List<String> overlapTracks = new ArrayList<>();
}
}
