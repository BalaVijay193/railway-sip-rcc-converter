package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccDto {
    private String rccId;
    private String yardId;
    private String yardName;
    private int totalRoutes;
    private List<RccEntryDto> entries;
    private long generatedAt;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class RccEntryDto {
    private String routeName;
    private String entrySignal;
    private String exitSignal;
    private Set<String> controlTracks;
    private Set<String> pointNormal;
    private Set<String> pointReverse;
    private Set<String> conflictingRoutes;
}
