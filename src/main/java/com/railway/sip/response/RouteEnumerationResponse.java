package com.railway.sip.response;

import com.railway.sip.dto.RouteDto;
import com.railway.sip.model.Route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Response containing enumerated routes
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteEnumerationResponse {
    
    private boolean success;
    private String yardId;
    private int totalRoutes;
    @Builder.Default
    private List<Route> routes = new ArrayList<>();
    
    private long processingTime;
    private String message;
    private long timestamp = System.currentTimeMillis();
}
