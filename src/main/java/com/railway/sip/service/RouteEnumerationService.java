package com.railway.sip.service;

import com.railway.sip.algorithm.RouteEnumerationAlgorithm;
import com.railway.sip.graph.YardGraph;
import com.railway.sip.graph.YardNode;
import com.railway.sip.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RouteEnumerationService {
    private final Map<String, Set<Route>> routeCache = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    
    /**
     * Enumerate all possible routes in yard
     */
    public Set<Route> enumerateAllRoutes(YardGraph graph) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        
        // Parallel enumeration for UP and DOWN directions
        Future<Set<Route>> upRoutes = executorService.submit(() -> 
            RouteEnumerationAlgorithm.enumerateRoutes(graph, YardNode.Direction.UP));
        
        Future<Set<Route>> downRoutes = executorService.submit(() -> 
            RouteEnumerationAlgorithm.enumerateRoutes(graph, YardNode.Direction.DOWN));
        
        Set<Route> allRoutes = new HashSet<>(upRoutes.get());
        allRoutes.addAll(downRoutes.get());
        
        // Cache routes
        routeCache.put(graph.getYardId(), allRoutes);
        
        long duration = System.currentTimeMillis() - startTime;
    //    log.info("Route enumeration completed in {}ms: {} total routes", duration, allRoutes.size());
        
        return allRoutes;
    }
    
    /**
     * Get routes by type (MAINLINE, SHUNT, CALLING_ON)
     */
    public Set<Route> getRoutesByType(String yardId, Route.RouteType type) {
        Set<Route> allRoutes = routeCache.get(yardId);
        if (allRoutes == null) {
            return new HashSet<>();
        }
        
        return allRoutes.stream()
            .filter(r -> r.getType() == type)
            .collect(Collectors.toSet());
    }
    
    /**
     * Get routes originating from specific signal
     */
    public Set<Route> getRoutesFromSignal(String yardId, String signalId) {
        Set<Route> allRoutes = routeCache.get(yardId);
        if (allRoutes == null) {
            return new HashSet<>();
        }
        
        return allRoutes.stream()
            .filter(r -> r.getEntrySignalId().equals(signalId))
            .collect(Collectors.toSet());
    }
    
    public Set<Route> getRoutes(String yardId) {
        return routeCache.getOrDefault(yardId, new HashSet<>());
    }
}
