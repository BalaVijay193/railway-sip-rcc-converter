package com.railway.sip.controller;

import com.railway.sip.dto.*;
import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import com.railway.sip.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import com.railway.sip.request.RouteEnumerationRequest;
import com.railway.sip.request.ConflictDetectionRequest;
import com.railway.sip.response.RouteEnumerationResponse;
import com.railway.sip.response.ConflictDetectionResponse;



@Slf4j
@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {
    
    @Autowired
    private YardGraphService yardGraphService;
    
    @Autowired
    private RouteEnumerationService routeEnumerationService;
    
    @Autowired
    private ConflictDetectionService conflictDetectionService;
    
    /**
     * Enumerate all routes in yard
     * POST /api/routes/enumerate
     */
    @PostMapping("/enumerate")
    public ResponseEntity<RouteEnumerationResponse> enumerateRoutes(
            @RequestBody RouteEnumerationRequest request) 
            throws ExecutionException, InterruptedException {
        
        //      log.info("Enumerating routes for yard: {}", request.getYardId());
        
        YardGraph graph = yardGraphService.getYardGraph(request.getYardId());
        if (graph == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Set<Route> allRoutes = routeEnumerationService.enumerateAllRoutes(graph);
        
        // Filter by type if specified
        Set<Route> filteredRoutes = allRoutes;
        if (!"ALL".equals(request.getRouteType())) {
            Route.RouteType type = Route.RouteType.valueOf(request.getRouteType());
            filteredRoutes = routeEnumerationService.getRoutesByType(request.getYardId(), type);
        }
        
        List<RouteDto> routeDtos = filteredRoutes.stream()
            .map(this::convertToDto)
            .sorted(Comparator.comparing(RouteDto::getRouteName))
            .collect(Collectors.toList());
        
        RouteEnumerationResponse response = RouteEnumerationResponse.builder()
            .yardId(request.getYardId())
            .totalRoutes(filteredRoutes.size())
            .routes(routeDtos)
            .generatedAt(System.currentTimeMillis())
            .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get specific route details
     * GET /api/routes/{routeId}
     */
    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDetailDto> getRouteDetails(@PathVariable String routeId) {
        // In real implementation, would fetch from service
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Detect conflicts between routes
     * POST /api/routes/detect-conflicts
     */
    @PostMapping("/detect-conflicts")
    public ResponseEntity<ConflictDetectionResponse> detectConflicts(
            @RequestBody ConflictDetectionRequest request) {
        
        //      log.info("Detecting conflicts for {} routes", request.getRouteIds().size());
        
        YardGraph graph = yardGraphService.getYardGraph(request.getYardId());
        Set<Route> routes = routeEnumerationService.getRoutes(request.getYardId()).stream()
            .filter(r -> request.getRouteIds().contains(r.getRouteId()))
            .collect(Collectors.toSet());
        
        Map<String, Set<ConflictDetectionService.RouteConflict>> conflicts = 
            conflictDetectionService.detectAllConflicts(routes, graph);
        
        List<ConflictDto> conflictDtos = new ArrayList<>();
        conflicts.forEach((routeId, routeConflicts) -> {
            routeConflicts.forEach(conflict -> {
                conflictDtos.add(ConflictDto.builder()
                    .route1Id(conflict.getRoute1Id())
                    .route2Id(conflict.getRoute2Id())
                    .type(conflict.getType().name())
                    .severity(conflict.getSeverity().name())
                    .description(conflict.getDescription())
                    .build());
            });
        });
        
        ConflictDetectionResponse response = ConflictDetectionResponse.builder()
            .yardId(request.getYardId())
            .totalConflicts(conflictDtos.size())
            .conflicts(conflictDtos)
            .detectedAt(System.currentTimeMillis())
            .build();
        
        return ResponseEntity.ok(response);
    }
    
    private RouteDto convertToDto(Route route) {
        return RouteDto.builder()
            .routeId(route.getRouteId())
            .routeName(route.getRouteName())
            .entrySignal(route.getEntrySignalId())
            .exitSignal(route.getExitSignalId())
            .type(route.getType().name())
            .direction(route.getDirection().name())
            .controlTrackCount(route.getControlTrackIds().size())
            .overlapTrackCount(route.getOverlapTrackIds().size())
            .pointCount(route.getPointPositions().size())
            .build();
    }
}
