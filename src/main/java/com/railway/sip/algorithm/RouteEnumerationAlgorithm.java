package com.railway.sip.algorithm;

import com.railway.sip.graph.YardGraph;
import com.railway.sip.graph.YardNode;
import com.railway.sip.graph.YardEdge;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Algorithm 1: Route enumeration using DFS
 * Time Complexity: O(4 * |TCP|)
 */
@Slf4j
public class RouteEnumerationAlgorithm {
    
    public static Set<Route> enumerateRoutes(YardGraph graph, YardNode.Direction direction) {
        Set<Route> allRoutes = new HashSet<>();
        
        List<YardNode> signalNodes = graph.getSignalNodes().stream()
            .filter(s -> s.getDirection() == direction || s.getDirection() == YardNode.Direction.BOTH)
            .collect(Collectors.toList());
        
        log.info("Enumerating routes for direction {}: {} entry signals", direction, signalNodes.size());
        
        for (YardNode entrySignal : signalNodes) {
            Set<Route> routesFromSignal = enumerateRoutesFromSignal(graph, entrySignal, direction);
            allRoutes.addAll(routesFromSignal);
        }
        
        return allRoutes;
    }
    
    private static Set<Route> enumerateRoutesFromSignal(YardGraph graph, YardNode entrySignal, 
                                                        YardNode.Direction direction) {
        Set<Route> routes = new HashSet<>();
        graph.resetVisited();
        
        // Use BFS to find all routes
        Queue<RouteSearchState> queue = new LinkedList<>();
        queue.add(new RouteSearchState(entrySignal, null, false));
        
        while (!queue.isEmpty()) {
            RouteSearchState state = queue.poll();
            YardNode currentNode = state.currentNode;
            YardNode prevSignal = state.prevSignal;
            
            // Check if we found exit signal
            if (currentNode.getType() == YardNode.NodeType.SIGNAL && 
                currentNode.getDirection() == direction &&
                prevSignal != null &&
                !currentNode.equals(prevSignal)) {
                
                Route route = createRoute(graph, prevSignal, currentNode, direction);
                routes.add(route);
            }
            
            // Continue traversal
            for (YardEdge edge : graph.getOutgoingEdges(currentNode.getNodeId())) {
                if (edge.getDirection() == direction || edge.getDirection() == YardNode.Direction.BOTH) {
                    YardNode nextNode = graph.getNodes().get(edge.getTargetNodeId());
                    
                    YardNode nextSignal = prevSignal;
                    if (currentNode.getType() == YardNode.NodeType.SIGNAL && 
                        currentNode.getDirection() == direction) {
                        nextSignal = currentNode;
                    }
                    
                    queue.add(new RouteSearchState(nextNode, nextSignal, false));
                }
            }
        }
        
        return routes;
    }
    
    private static Route createRoute(YardGraph graph, YardNode entrySignal, 
                                    YardNode exitSignal, YardNode.Direction direction) {
        String routeId = entrySignal.getNodeId() + "_" + exitSignal.getNodeId();
        List<String> trackPath = findTrackPath(graph, entrySignal, exitSignal, direction);
        
        return Route.builder()
            .routeId(routeId)
            .routeName(generateRouteName(entrySignal, exitSignal))
            .entrySignalId(entrySignal.getNodeId())
            .exitSignalId(exitSignal.getNodeId())
            .direction(direction)
            .controlTrackIds(trackPath)
            .pointPositions(new HashMap<>())
            .overlapTrackIds(new ArrayList<>())
            .isolationPointIds(new ArrayList<>())
            .crankHandleIds(new ArrayList<>())
            .levelCrossingIds(new ArrayList<>())
            .type(determineRouteType(entrySignal, exitSignal))
            .build();
    }
    
    private static List<String> findTrackPath(YardGraph graph, YardNode start, 
                                             YardNode end, YardNode.Direction direction) {
        List<String> path = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        
        queue.add(start.getNodeId());
        parent.put(start.getNodeId(), null);
        
        while (!queue.isEmpty()) {
            String nodeId = queue.poll();
            
            if (nodeId.equals(end.getNodeId())) {
                String current = nodeId;
                while (current != null) {
                    path.add(0, current);
                    current = parent.get(current);
                }
                break;
            }
            
            for (YardEdge edge : graph.getOutgoingEdges(nodeId)) {
                if (edge.getDirection() == direction || edge.getDirection() == YardNode.Direction.BOTH) {
                    String targetId = edge.getTargetNodeId();
                    if (!parent.containsKey(targetId)) {
                        parent.put(targetId, nodeId);
                        queue.add(targetId);
                    }
                }
            }
        }
        
        return path;
    }
    
    private static String generateRouteName(YardNode entry, YardNode exit) {
        return entry.getName() + "-" + exit.getName();
    }
    
    private static Route.RouteType determineRouteType(YardNode entrySignal, YardNode exitSignal) {
        if (entrySignal.getSignalType() == YardNode.SignalType.HOME) {
            return Route.RouteType.MAINLINE;
        } else if (entrySignal.getSignalType() == YardNode.SignalType.CALLING_ON) {
            return Route.RouteType.CALLING_ON;
        }
        return Route.RouteType.SHUNT;
    }
    
    static class RouteSearchState {
        YardNode currentNode;
        YardNode prevSignal;
        boolean signalFound;
        
        RouteSearchState(YardNode currentNode, YardNode prevSignal, boolean signalFound) {
            this.currentNode = currentNode;
            this.prevSignal = prevSignal;
            this.signalFound = signalFound;
        }
    }
}
