package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalDto {
    private String signalId;
    private String signalName;
    private String signalType;
    private double positionX;
    private double positionY;
    private String status;
}
