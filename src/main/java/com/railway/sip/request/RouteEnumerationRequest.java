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

    // ADD THIS METHOD
    public String getRouteType() {
        return this.routeType;
    }

}
