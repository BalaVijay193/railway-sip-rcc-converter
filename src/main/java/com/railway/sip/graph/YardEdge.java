package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardEdge {
    private String edgeId;
    private String sourceNodeId;
    private String targetNodeId;
    private double weight;
    private YardNode.Direction direction;
    private String type;
    private boolean bidirectional;
}
