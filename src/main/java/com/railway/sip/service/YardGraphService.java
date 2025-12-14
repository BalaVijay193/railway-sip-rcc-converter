package com.railway.sip.service;

import com.railway.sip.dto.*;
import com.railway.sip.graph.YardGraph;
import com.railway.sip.graph.YardNode;
import com.railway.sip.graph.YardEdge;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class YardGraphService {
    
    private final Map<String, YardGraph> yards = new ConcurrentHashMap<>();
    
    /**
     * Create a new yard with given specifications
     */
    public YardDto createYard(String yardId, String yardName, String location, String type) {
        YardGraph yard = YardGraph.builder()
                .yardId(yardId)
                .yardName(yardName)
                .location(location)
                .yardType(type)
                .nodes(new HashMap<>())
                .edges(new HashMap<>())
                .build();
        
        yards.put(yardId, yard);
        
        return YardDto.builder()
                .yardId(yardId)
                .yardName(yardName)
                .location(location)
                .yardType(type)
                .nodeCount(0)
                .edgeCount(0)
                .build();
    }
    
    /**
     * Get yard by ID
     */
    public YardDto getYard(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        return YardDto.builder()
                .yardId(yard.getYardId())
                .yardName(yard.getYardName())
                .location(yard.getLocation())
                .yardType(yard.getYardType())
                .nodeCount(yard.getNodes().size())
                .edgeCount(yard.getEdges().size())
                .build();
    }
    
    /**
     * Add a track to the yard
     */
    public TrackDto addTrack(String yardId, String trackId, String trackName, double length, String trackType) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode node = YardNode.builder()
                .nodeId(trackId)
                .nodeName(trackName)
                .nodeType(YardNode.NodeType.TRACK)
                .length(length)
                .build();
        
        yard.getNodes().put(trackId, node);
        
        return TrackDto.builder()
                .trackId(trackId)
                .trackName(trackName)
                .length(length)
                .trackType(trackType)
                .build();
    }
    
    /**
     * Add a signal to the yard
     */
    public SignalDto addSignal(String yardId, String signalId, String signalName, String signalType, double x, double y) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode.SignalType type = YardNode.SignalType.valueOf(signalType.toUpperCase());
        
        YardNode node = YardNode.builder()
                .nodeId(signalId)
                .nodeName(signalName)
                .nodeType(YardNode.NodeType.SIGNAL)
                .signalType(type)
                .x(x)
                .y(y)
                .build();
        
        yard.getNodes().put(signalId, node);
        
        return SignalDto.builder()
                .signalId(signalId)
                .signalName(signalName)
                .signalType(signalType)
                .x(x)
                .y(y)
                .build();
    }
    
    /**
     * Add a point to the yard
     */
    public PointDto addPoint(String yardId, String pointId, String pointName, String pointType, double x, double y) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode node = YardNode.builder()
                .nodeId(pointId)
                .nodeName(pointName)
                .nodeType(YardNode.NodeType.POINT)
                .pointType(pointType)
                .x(x)
                .y(y)
                .build();
        
        yard.getNodes().put(pointId, node);
        
        return PointDto.builder()
                .pointId(pointId)
                .pointName(pointName)
                .pointType(pointType)
                .x(x)
                .y(y)
                .build();
    }
    
    /**
     * Add connection between two nodes
     */
    public ConnectionDto addConnection(String yardId, String fromNodeId, String toNodeId, String connectionType) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        String edgeId = fromNodeId + "_" + toNodeId;
        YardEdge edge = YardEdge.builder()
                .edgeId(edgeId)
                .fromNode(fromNodeId)
                .toNode(toNodeId)
                .edgeType(connectionType)
                .build();
        
        yard.getEdges().put(edgeId, edge);
        
        return ConnectionDto.builder()
                .connectionId(edgeId)
                .fromNodeId(fromNodeId)
                .toNodeId(toNodeId)
                .connectionType(connectionType)
                .build();
    }
    
    /**
     * Validate yard configuration
     */
    public ValidationDto validateYard(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) {
            return ValidationDto.builder()
                    .isValid(false)
                    .errorMessage("Yard not found")
                    .build();
        }
        
        return ValidationDto.builder()
                .isValid(true)
                .message("Yard is valid")
                .build();
    }
    
    /**
     * List all tracks in the yard
     */
    public List<TrackDto> listTracks(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return new ArrayList<>();
        
        List<TrackDto> tracks = new ArrayList<>();
        yard.getNodes().values().stream()
                .filter(n -> n.getNodeType() == YardNode.NodeType.TRACK)
                .forEach(n -> tracks.add(TrackDto.builder()
                        .trackId(n.getNodeId())
                        .trackName(n.getNodeName())
                        .length(n.getLength())
                        .build()));
        
        return tracks;
    }
    
    /**
     * List all signals in the yard
     */
    public List<SignalDto> listSignals(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return new ArrayList<>();
        
        List<SignalDto> signals = new ArrayList<>();
        yard.getNodes().values().stream()
                .filter(n -> n.getNodeType() == YardNode.NodeType.SIGNAL)
                .forEach(n -> signals.add(SignalDto.builder()
                        .signalId(n.getNodeId())
                        .signalName(n.getNodeName())
                        .signalType(n.getSignalType().name())
                        .x(n.getX())
                        .y(n.getY())
                        .build()));
        
        return signals;
    }
    
    /**
     * List all points in the yard
     */
    public List<PointDto> listPoints(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return new ArrayList<>();
        
        List<PointDto> points = new ArrayList<>();
        yard.getNodes().values().stream()
                .filter(n -> n.getNodeType() == YardNode.NodeType.POINT)
                .forEach(n -> points.add(PointDto.builder()
                        .pointId(n.getNodeId())
                        .pointName(n.getNodeName())
                        .pointType(n.getPointType())
                        .x(n.getX())
                        .y(n.getY())
                        .build()));
        
        return points;
    }
    
    /**
     * List all connections in the yard
     */
    public List<ConnectionDto> listConnections(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return new ArrayList<>();
        
        List<ConnectionDto> connections = new ArrayList<>();
        yard.getEdges().values().forEach(e -> connections.add(ConnectionDto.builder()
                .connectionId(e.getEdgeId())
                .fromNodeId(e.getFromNode())
                .toNodeId(e.getToNode())
                .connectionType(e.getEdgeType())
                .build()));
        
        return connections;
    }
    
    /**
     * Delete a yard
     */
    public boolean deleteYard(String yardId) {
        return yards.remove(yardId) != null;
    }
}
