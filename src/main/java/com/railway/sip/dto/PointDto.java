package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {
    private String pointId;
    private String pointName;
    private String pointType;
    private double positionX;
    private double positionY;
    private String status;
}
