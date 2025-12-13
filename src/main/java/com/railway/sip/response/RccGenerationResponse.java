package com.railway.sip.response;

import com.railway.sip.dto.RccDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response containing generated RCC
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccGenerationResponse {
    
    private boolean success;
    private String rccId;
    private String yardId;
    private String yardName;
    private int totalRoutes;
    private RccDto rcc;
    private long processingTimeMs;
    private String exportFile; // Path or URL to exported file
    private String message;
    private long timestamp = System.currentTimeMillis();
}
