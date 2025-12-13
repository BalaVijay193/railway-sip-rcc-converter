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
public class AddSignalRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Signal ID is required")
    private String signalId;
    
    @NotBlank(message = "Signal name is required")
    private String signalName;
    
    @NotBlank(message = "Signal type is required")
    private String signalType;
    
    private double positionX;
    private double positionY;
}
