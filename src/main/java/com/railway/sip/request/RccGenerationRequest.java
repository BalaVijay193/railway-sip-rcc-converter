package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Request to generate Route Control Chart (RCC)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccGenerationRequest {
    
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotEmpty(message = "At least one route must be selected")
    private Set<String> selectedRoutes;
    
    @Builder.Default
    private String exportFormat = "JSON"; // JSON, XML, PDF
    
    @Builder.Default
    private boolean includeOverlaps = true;
    
    @Builder.Default
    private boolean includeIsolationPoints = true;
    
    @Builder.Default
    private boolean includeConflicts = true;
}
