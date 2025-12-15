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
    
    public YardDto createYard(String yardId, String yardName, String location, String description) {
        YardGraph yard = new YardGraph();
        yard.setYardId(yardId);
        yard.setYardName(yardName);
        yard.setLocation(location);
        yard.setYardType(description);
        yard.setNodes(new HashMap<>());
        yard.setEdges(new ArrayList<>());
        yards.put(yardId, yard);
        
        YardDto dto = new YardDto();
        dto.setYardId(yardId);
        dto.setYardName(yardName);
        dto.setLocation(location);
    //    dto.setYardType(description);
      //  dto.setNodeCount(0);
      //  dto.setEdgeCount(0);
        return dto;
    }
    
    public YardDto getYard(String yardId) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardDto dto = new YardDto();
        dto.setYardId(yard.getYardId());
        dto.setYardName(yard.getYardName());
   //     dto.setLocation(yard.getLocation());
        return dto;
    }
    
    public TrackDto addTrack(String yardId, String trackId, String trackName, double length, String trackType) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode node = new YardNode();
        node.setNodeId(trackId);
        node.setNodeName(trackName);
        node.setNodeType("TRACK");
        node.setLength(length);
        
        yard.getNodes().put(trackId, node);
        
        TrackDto dto = new TrackDto();
        dto.setTrackId(trackId);
        dto.setTrackName(trackName);
        dto.setLength(length);
        dto.setTrackType(trackType);
        return dto;
    }
    
    public SignalDto addSignal(String yardId, String signalId, String signalName, String signalType, double x, double y) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode node = new YardNode();
        node.setNodeId(signalId);
        node.setNodeName(signalName);
        node.setNodeType("SIGNAL");
        node.setX(x);
        node.setY(y);
        
        try {
            node.setSignalType(YardNode.SignalType.valueOf(signalType.toUpperCase()));
        } catch (IllegalArgumentException e) {
            node.setSignalType(YardNode.SignalType.HOME);
        }
        
        yard.getNodes().put(signalId, node);
        
        SignalDto dto = new SignalDto();
        dto.setSignalId(signalId);
        dto.setSignalName(signalName);
        dto.setSignalType(signalType);
        dto.setX(x);
        dto.setY(y);
        return dto;
    }
    
    public PointDto addPoint(String yardId, String pointId, String pointName, String pointType, double x, double y) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        YardNode node = new YardNode();
        node.setNodeId(pointId);
        node.setNodeName(pointName);
        node.setNodeType("POINT");
        node.setPointType(pointType);
        node.setX(x);
        node.setY(y);
        
        yard.getNodes().put(pointId, node);
        
        PointDto dto = new PointDto();
        dto.setPointId(pointId);
        dto.setPointName(pointName);
        dto.setPointType(pointType);
        dto.setX(x);
        dto.setY(y);
        return dto;
    }
    
    public ConnectionDto addConnection(String yardId, String fromNodeId, String toNodeId, String connectionType) {
        YardGraph yard = yards.get(yardId);
        if (yard == null) return null;
        
        String edgeId = fromNodeId + "_" + toNodeId;
        YardEdge edge = new YardEdge();
        edge.setEdgeId(edgeId);
        edge.setFromNode(fromNodeId);
        edge.setToNode(toNodeId);
        edge.setEdgeType(connectionType);
        
        yard.getEdges().add(edge);
        
        ConnectionDto dto = new ConnectionDto();
        dto.setConnectionId(edgeId);
        dto.setFromNodeId(fromNodeId);
        dto.setToNodeId(toNodeId);
        dto.setConnectionType(connectionType);
        return dto;
    }
    
    public ValidationDto validateYard(String yardId) {
        YardGraph yard = yards.get(yardId);
        
        ValidationDto dto = new ValidationDto();
        if (yard == null) {
            dto.setValid(false);
            dto.setErrorMessage("Yard not found");
        } else {
            dto.setValid(true);
            dto.setMessage("Yard is valid");
        }
        return dto;
    }
    
    public List<TrackDto> listTracks(String yardId) {
        YardGraph yard = yards.get(yardId);
        List<TrackDto> tracks = new ArrayList<>();
        
        if (yard == null) return tracks;
        
        for (YardNode node : yard.getNodes().values()) {
            if ("TRACK".equals(node.getNodeType())) {
                TrackDto dto = new TrackDto();
                dto.setTrackId(node.getNodeId());
                dto.setTrackName(node.getNodeName());
                dto.setLength(node.getLength());
                tracks.add(dto);
            }
        }
        return tracks;
    }
    
    public List<SignalDto> listSignals(String yardId) {
        YardGraph yard = yards.get(yardId);
        List<SignalDto> signals = new ArrayList<>();
        
        if (yard == null) return signals;
        
        for (YardNode node : yard.getNodes().values()) {
            if ("SIGNAL".equals(node.getNodeType()) && node.getSignalType() != null) {
                SignalDto dto = new SignalDto();
                dto.setSignalId(node.getNodeId());
                dto.setSignalName(node.getNodeName());
                dto.setSignalType(node.getSignalType().name());
                dto.setX(node.getX());
                dto.setY(node.getY());
                signals.add(dto);
            }
        }
        return signals;
    }
    
    public List<PointDto> listPoints(String yardId) {
        YardGraph yard = yards.get(yardId);
        List<PointDto> points = new ArrayList<>();
        
        if (yard == null) return points;
        
        for (YardNode node : yard.getNodes().values()) {
            if ("POINT".equals(node.getNodeType())) {
                PointDto dto = new PointDto();
                dto.setPointId(node.getNodeId());
                dto.setPointName(node.getNodeName());
                dto.setPointType(node.getPointType());
                dto.setX(node.getX());
                dto.setY(node.getY());
                points.add(dto);
            }
        }
        return points;
    }
    
    public List<ConnectionDto> listConnections(String yardId) {
        YardGraph yard = yards.get(yardId);
        List<ConnectionDto> connections = new ArrayList<>();
        
        if (yard == null) return connections;
        
        for (YardEdge edge : yard.getEdges()) {
            ConnectionDto dto = new ConnectionDto();
            dto.setConnectionId(edge.getEdgeId());
            dto.setFromNodeId(edge.getFromNode());
            dto.setToNodeId(edge.getToNode());
            dto.setConnectionType(edge.getEdgeType());
            connections.add(dto);
        }
        return connections;
    }
    
    public boolean deleteYard(String yardId) {
        return yards.remove(yardId) != null;
    }
    
    public YardGraph getYardGraph(String yardId) {
        return yards.get(yardId);
    }
}
