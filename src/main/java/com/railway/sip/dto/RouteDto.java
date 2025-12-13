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
}
