package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Source element ID is required")
    private String sourceId;
    
    @NotBlank(message = "Destination element ID is required")
    private String destinationId;
    
    @NotBlank(message = "Connection type is required")
    private String connectionType;
}
