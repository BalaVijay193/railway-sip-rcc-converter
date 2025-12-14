package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccEntryDto1 {
    private String routeId;
    private String routeName;
    private String entrySignalId;
    private String exitSignalId;
    private List<String> controlTracks;
    private List<String> overlapTracks;
    private Set<String> conflictingRoutes;
}
