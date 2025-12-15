package com.railway.sip.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;
import com.railway.sip.graph.YardEdge;
import com.railway.sip.graph.YardNode;

/**
 * Graph representation of railway yard layout
 */
//@Data
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

    public String getYardId() {
		return yardId;
	}

	public void setYardId(String yardId) {
		this.yardId = yardId;
	}

	public String getYardName() {
		return yardName;
	}

	public void setYardName(String yardName) {
		this.yardName = yardName;
	}

	public Map<String, List<YardEdge>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Map<String, List<YardEdge>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public Map<Double, List<String>> getHorizontalIndex() {
		return horizontalIndex;
	}

	public void setHorizontalIndex(Map<Double, List<String>> horizontalIndex) {
		this.horizontalIndex = horizontalIndex;
	}

	public void setNodes(Map<String, YardNode> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(List<YardEdge> edges) {
		this.edges = edges;
	}

	public void addNode(YardNode node) {
        nodes.put(node.getNodeId(), node);
        adjacencyList.putIfAbsent(node.getNodeId(), new ArrayList<>());
        Double xCoord = node.getxRight();
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
                .filter(node -> Math.hypot(node.getxRight() - x, node.getyRight() - y) <= threshold)
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

    // NEWLY ADDED METHODS
    public Set<String> getControlTrackIds(String signalId) {
        YardNode signal = nodes.get(signalId);
        if (signal == null || signal.getType() != YardNode.NodeType.SIGNAL) {
            return new HashSet<>();
        }
        
        Set<String> controlTracks = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(signalId);
        visited.add(signalId);
        
        while (!queue.isEmpty()) {
            String currentId = queue.poll();
            YardNode current = nodes.get(currentId);
            
            if (current != null && current.getType() == YardNode.NodeType.TRACK) {
                controlTracks.add(currentId);
            }
            
            for (YardEdge edge : getOutgoingEdges(currentId)) {
                if (!visited.contains(edge.getTargetNodeId())) {
                    visited.add(edge.getTargetNodeId());
                    queue.add(edge.getTargetNodeId());
                }
            }
        }
        
        return controlTracks;
    }

    public YardNode.Direction getDirection(String nodeId) {
        YardNode node = nodes.get(nodeId);
        return node != null ? node.getDirection() : YardNode.Direction.BOTH;
    }

    public Set<String> getOverlapTracks(String signalId) {
        return new HashSet<>(getControlTrackIds(signalId));
    }

    public List<YardNode> getAdjacentNodes(String nodeId) {
        return getSuccessorNodes(nodeId);
    }

    public List<YardEdge> getIncomingEdges(String nodeId) {
        List<YardEdge> incomingEdges = new ArrayList<>();
        for (YardEdge edge : edges) {
            if (edge.getTargetNodeId().equals(nodeId)) {
                incomingEdges.add(edge);
            }
        }
        return incomingEdges;
    }

    public YardNode getNodeById(String nodeId) {
        return nodes.get(nodeId);
    }

    public Map<String, YardNode> getNodes() {
        return nodes;
    }

    public List<YardEdge> getEdges() {
        return edges;
    }

    public boolean hasPath(String from, String to) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(from);
        visited.add(from);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) {
                return true;
            }
            
            for (YardEdge edge : getOutgoingEdges(current)) {
                if (!visited.contains(edge.getTargetNodeId())) {
                    visited.add(edge.getTargetNodeId());
                    queue.add(edge.getTargetNodeId());
                }
            }
        }
        return false;
    }

	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
		
	}

	public void setYardType(String description) {
		// TODO Auto-generated method stub
		
	}
	
	
}
