package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * Request to enumerate routes from a yard layout
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteEnumerationRequest {
    
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @Builder.Default
    private String routeType = "ALL"; // ALL, MAINLINE, SHUNT, CALLING_ON
}
