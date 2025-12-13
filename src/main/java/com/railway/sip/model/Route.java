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

    public enum RouteType {
        MAINLINE,
        CALLING_ON,
        SHUNT,
        SIDING
    }
}
