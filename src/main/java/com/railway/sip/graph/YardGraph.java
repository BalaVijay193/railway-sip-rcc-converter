package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Graph representation of railway yard layout
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardGraph {
    private String yardId;
    private String yardName;
    private Map<String, YardNode> nodes = new HashMap<>();
    private Map<String, List<YardEdge>> adjacencyList = new HashMap<>();
    private List<YardEdge> edges = new ArrayList<>();
    private Map<Double, List<String>> horizontalIndex = new HashMap<>();
    
    public void addNode(YardNode node) {
        nodes.put(node.getNodeId(), node);
        adjacencyList.putIfAbsent(node.getNodeId(), new ArrayList<>());
        
        Double xCoord = node.getXRight();
        horizontalIndex.computeIfAbsent(xCoord, k -> new ArrayList<>())
                       .add(node.getNodeId());
    }
    
    public void addEdge(YardEdge edge) {
        edges.add(edge);
        adjacencyList.get(edge.getSourceNodeId()).add(edge);
    }
    
    public List<YardEdge> getOutgoingEdges(String nodeId) {
        return adjacencyList.getOrDefault(nodeId, new ArrayList<>());
    }
    
    public List<YardNode> getSuccessorNodes(String nodeId) {
        return getOutgoingEdges(nodeId).stream()
            .map(edge -> nodes.get(edge.getTargetNodeId()))
            .collect(Collectors.toList());
    }
    
    public List<YardNode> findNearbyNodes(double x, double y, double threshold) {
        return nodes.values().stream()
            .filter(node -> Math.hypot(node.getXRight() - x, node.getYRight() - y) <= threshold)
            .collect(Collectors.toList());
    }
    
    public void resetVisited() {
        nodes.values().forEach(node -> node.setVisited(false));
    }
    
    public List<YardNode> getSignalNodes() {
        return nodes.values().stream()
            .filter(n -> n.getType() == YardNode.NodeType.SIGNAL)
            .collect(Collectors.toList());
    }
    
    public List<YardNode> getTrackNodes(YardNode.Direction direction) {
        return nodes.values().stream()
            .filter(n -> n.getType() == YardNode.NodeType.TRACK && 
                   (n.getDirection() == direction || n.getDirection() == YardNode.Direction.BOTH))
            .collect(Collectors.toList());
    }
    
    public List<YardNode> getPointNodes() {
        return nodes.values().stream()
            .filter(n -> n.getType() == YardNode.NodeType.POINT)
            .collect(Collectors.toList());
    }
}
