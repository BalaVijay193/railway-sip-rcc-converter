package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTrackRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Track ID is required")
    private String trackId;
    
    @NotBlank(message = "Track name is required")
    private String trackName;
    
    @Min(value = 0, message = "Length must be positive")
    private double length;
    
    private String type;
}
