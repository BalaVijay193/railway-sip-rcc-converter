package com.railway.sip.response;

import com.railway.sip.dto.RouteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<RouteDto> routes;
    private long processingTimeMs;
    private String message;
    private long timestamp = System.currentTimeMillis();
}
