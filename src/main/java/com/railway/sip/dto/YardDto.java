package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardDto {
    private String yardId;
    private String yardName;
    private String description;
    private String location;
    private int trackCount;
    private int signalCount;
    private int pointCount;
    private String status;
}
