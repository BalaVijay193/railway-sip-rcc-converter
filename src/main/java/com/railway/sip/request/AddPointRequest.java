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
public class AddPointRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Point ID is required")
    private String pointId;
    
    @NotBlank(message = "Point name is required")
    private String pointName;
    
    @NotBlank(message = "Point type is required")
    private String pointType;
    
    private double positionX;
    private double positionY;
}
