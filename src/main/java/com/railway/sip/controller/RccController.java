package com.railway.sip.controller;

import com.railway.sip.dto.*;
import com.railway.sip.graph.YardGraph;
import com.railway.sip.model.Route;
import com.railway.sip.model.RouteControlChart;
import com.railway.sip.request.RccGenerationRequest;
import com.railway.sip.response.RccGenerationResponse;
import com.railway.sip.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/rcc")
@CrossOrigin(origins = "*")
public class RccController {
    
    @Autowired
    private YardGraphService yardGraphService;
    
    @Autowired
    private RouteEnumerationService routeEnumerationService;
    
    @Autowired
    private RccGenerationService rccGenerationService;
    
    /**
     * Generate RCC from selected routes
     * POST /api/rcc/generate
     */
    @PostMapping("/generate")
    public ResponseEntity<RccGenerationResponse> generateRcc(
            @RequestBody RccGenerationRequest request) {
        
        log.info("Generating RCC for yard: {} with {} routes", 
                 request.getYardId(), request.getSelectedRoutes().size());
        
        YardGraph graph = yardGraphService.getYardGraph(request.getYardId());
        if (graph == null) {
            return ResponseEntity.badRequest().body(RccGenerationResponse.builder()
                .success(false)
                .message("Yard not found: " + request.getYardId())
                .build());
        }
        
        Set<Route> allRoutes = routeEnumerationService.getRoutes(request.getYardId());
        Set<Route> selectedRoutes = allRoutes.stream()
            .filter(r -> request.getSelectedRoutes().contains(r.getRouteId()))
            .collect(Collectors.toSet());
        
        String rccId = "RCC_" + request.getYardId() + "_" + System.currentTimeMillis();
        RouteControlChart rcc = rccGenerationService.generateRcc(
            rccId,
            request.getYardId(),
            graph.getYardName(),
            selectedRoutes,
            graph
        );
        
        RccGenerationResponse response = RccGenerationResponse.builder()
            .success(true)
            .rccId(rccId)
            .yardId(request.getYardId())
            .totalRoutes(rcc.getTotalRoutes())
            .generatedAt(System.currentTimeMillis())
            .message("RCC generated successfully")
            .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get RCC details
     * GET /api/rcc/{rccId}
     */
    @GetMapping("/{rccId}")
    public ResponseEntity<RccDetailDto> getRccDetails(@PathVariable String rccId) {
        RouteControlChart rcc = rccGenerationService.getRcc(rccId);
        if (rcc == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<RccEntryDto> entries = rcc.getEntries().stream()
            .map(entry -> RccEntryDto.builder()
                .routeName(entry.getRouteName())
                .entrySignal(entry.getEntrySignal())
                .exitSignal(entry.getExitSignal())
                .controlTracks(entry.getControlTracks())
                .pointNormal(entry.getPointNormal())
                .pointReverse(entry.getPointReverse())
                .conflictingRoutes(entry.getConflictingRoutes())
                .build())
            .collect(Collectors.toList());
        
        RccDetailDto response = RccDetailDto.builder()
            .rccId(rcc.getRccId())
            .yardId(rcc.getYardId())
            .yardName(rcc.getYardName())
            .totalRoutes(rcc.getTotalRoutes())
            .entries(entries)
            .generatedAt(rcc.getCreatedAt())
            .build();
        
        return ResponseEntity.ok(response);
    }
}
