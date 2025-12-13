package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccDetailDto {
    private String rccId;
    private String yardId;
    private List<String> selectedRoutes;
    private List<String> conflicts;
    private List<String> overlaps;
    private List<String> isolationPoints;
    private String exportFormat;
    private String status;
}
