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
}
