package com.railway.sip.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.railway.sip.dto.*;
import com.railway.sip.request.*;
import com.railway.sip.response.*;
import com.railway.sip.service.*;
import com.railway.sip.exception.SipRccException;

/**
 * SIP Controller - Manages yard components (signals, tracks, points, connections)
 * Entry point for creating and managing railway yard layouts
 */
@Slf4j
@RestController
@RequestMapping("/api/sip")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SipController {

    @Autowired
    private YardGraphService yardGraphService;

    @Autowired
    private RouteEnumerationService routeEnumerationService;

    @Autowired
    private ConflictDetectionService conflictDetectionService;

    @Autowired
    private RccGenerationService rccGenerationService;

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        log.info("Health check requested");
        return ResponseEntity.ok().body(new Object() {
            public String status = "UP";
            public String message = "SIP-RCC Converter Service is running";
            public long timestamp = System.currentTimeMillis();
        });
    }

    /**
     * Create a new yard
     * POST /api/sip/yards
     */
    @PostMapping("/yards")
    public ResponseEntity<?> createYard(@Valid @RequestBody CreateYardRequest request) {
        log.info("Creating yard: {}", request.getYardId());
        try {
            YardDto yard = yardGraphService.createYard(
                request.getYardId(),
                request.getYardName(),
                request.getDescription(),
                request.getLocation()
            );
            log.info("Yard created successfully: {}", request.getYardId());
            return ResponseEntity.status(HttpStatus.CREATED).body(yard);
        } catch (Exception e) {
            log.error("Error creating yard: {}", request.getYardId(), e);
            throw new SipRccException("Failed to create yard: " + e.getMessage());
        }
    }

    /**
     * Get yard details by ID
     * GET /api/sip/yards/{yardId}
     */
    @GetMapping("/yards/{yardId}")
    public ResponseEntity<?> getYard(@PathVariable String yardId) {
        log.info("Fetching yard: {}", yardId);
        try {
            YardDto yard = yardGraphService.getYard(yardId);
            return ResponseEntity.ok(yard);
        } catch (Exception e) {
            log.error("Error fetching yard: {}", yardId, e);
            throw new SipRccException("Yard not found: " + yardId);
        }
    }

    /**
     * Add a track to a yard
     * POST /api/sip/yards/{yardId}/tracks
     */
    @PostMapping("/yards/{yardId}/tracks")
    public ResponseEntity<?> addTrack(
            @PathVariable String yardId,
            @Valid @RequestBody AddTrackRequest request) {
        log.info("Adding track {} to yard {}", request.getTrackId(), yardId);
        try {
            TrackDto track = yardGraphService.addTrack(
                yardId,
                request.getTrackId(),
                request.getTrackName(),
                request.getLength(),
                request.getType()
            );
            log.info("Track added successfully: {}", request.getTrackId());
            return ResponseEntity.status(HttpStatus.CREATED).body(track);
        } catch (Exception e) {
            log.error("Error adding track: {}", request.getTrackId(), e);
            throw new SipRccException("Failed to add track: " + e.getMessage());
        }
    }

    /**
     * Add a signal to a yard
     * POST /api/sip/yards/{yardId}/signals
     */
    @PostMapping("/yards/{yardId}/signals")
    public ResponseEntity<?> addSignal(
            @PathVariable String yardId,
            @Valid @RequestBody AddSignalRequest request) {
        log.info("Adding signal {} to yard {}", request.getSignalId(), yardId);
        try {
            SignalDto signal = yardGraphService.addSignal(
                yardId,
                request.getSignalId(),
                request.getSignalName(),
                request.getSignalType(),
                request.getPositionX(),
                request.getPositionY()
            );
            log.info("Signal added successfully: {}", request.getSignalId());
            return ResponseEntity.status(HttpStatus.CREATED).body(signal);
        } catch (Exception e) {
            log.error("Error adding signal: {}", request.getSignalId(), e);
            throw new SipRccException("Failed to add signal: " + e.getMessage());
        }
    }

    /**
     * Add a point/switch to a yard
     * POST /api/sip/yards/{yardId}/points
     */
    @PostMapping("/yards/{yardId}/points")
    public ResponseEntity<?> addPoint(
            @PathVariable String yardId,
            @Valid @RequestBody AddPointRequest request) {
        log.info("Adding point {} to yard {}", request.getPointId(), yardId);
        try {
            PointDto point = yardGraphService.addPoint(
                yardId,
                request.getPointId(),
                request.getPointName(),
                request.getPointType(),
                request.getPositionX(),
                request.getPositionY()
            );
            log.info("Point added successfully: {}", request.getPointId());
            return ResponseEntity.status(HttpStatus.CREATED).body(point);
        } catch (Exception e) {
            log.error("Error adding point: {}", request.getPointId(), e);
            throw new SipRccException("Failed to add point: " + e.getMessage());
        }
    }

    /**
     * Create a connection between two elements
     * POST /api/sip/yards/{yardId}/connections
     */
    @PostMapping("/yards/{yardId}/connections")
    public ResponseEntity<?> addConnection(
            @PathVariable String yardId,
            @Valid @RequestBody ConnectRequest request) {
        log.info("Creating connection from {} to {}", request.getSourceId(), request.getDestinationId());
        try {
            ConnectionDto connection = yardGraphService.addConnection(
                yardId,
                request.getSourceId(),
                request.getDestinationId(),
                request.getConnectionType()
            );
            log.info("Connection created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(connection);
        } catch (Exception e) {
            log.error("Error creating connection", e);
            throw new SipRccException("Failed to create connection: " + e.getMessage());
        }
    }

    /**
     * Validate the entire yard layout
     * POST /api/sip/yards/{yardId}/validate
     */
    @PostMapping("/yards/{yardId}/validate")
    public ResponseEntity<?> validateYard(@PathVariable String yardId) {
        log.info("Validating yard: {}", yardId);
        try {
            ValidationDto validation = yardGraphService.validateYard(yardId);
            log.info("Yard validation completed: {}", yardId);
            return ResponseEntity.ok(validation);
        } catch (Exception e) {
            log.error("Error validating yard: {}", yardId, e);
            throw new SipRccException("Failed to validate yard: " + e.getMessage());
        }
    }

    /**
     * List all tracks in a yard
     * GET /api/sip/yards/{yardId}/tracks
     */
    @GetMapping("/yards/{yardId}/tracks")
    public ResponseEntity<?> listTracks(@PathVariable String yardId) {
        log.info("Listing tracks for yard: {}", yardId);
        try {
            var tracks = yardGraphService.listTracks(yardId);
            return ResponseEntity.ok(tracks);
        } catch (Exception e) {
            log.error("Error listing tracks: {}", yardId, e);
            throw new SipRccException("Failed to list tracks: " + e.getMessage());
        }
    }

    /**
     * List all signals in a yard
     * GET /api/sip/yards/{yardId}/signals
     */
    @GetMapping("/yards/{yardId}/signals")
    public ResponseEntity<?> listSignals(@PathVariable String yardId) {
        log.info("Listing signals for yard: {}", yardId);
        try {
            var signals = yardGraphService.listSignals(yardId);
            return ResponseEntity.ok(signals);
        } catch (Exception e) {
            log.error("Error listing signals: {}", yardId, e);
            throw new SipRccException("Failed to list signals: " + e.getMessage());
        }
    }

    /**
     * List all points in a yard
     * GET /api/sip/yards/{yardId}/points
     */
    @GetMapping("/yards/{yardId}/points")
    public ResponseEntity<?> listPoints(@PathVariable String yardId) {
        log.info("Listing points for yard: {}", yardId);
        try {
            var points = yardGraphService.listPoints(yardId);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            log.error("Error listing points: {}", yardId, e);
            throw new SipRccException("Failed to list points: " + e.getMessage());
        }
    }

    /**
     * Get all connections in a yard
     * GET /api/sip/yards/{yardId}/connections
     */
    @GetMapping("/yards/{yardId}/connections")
    public ResponseEntity<?> listConnections(@PathVariable String yardId) {
        log.info("Listing connections for yard: {}", yardId);
        try {
            var connections = yardGraphService.listConnections(yardId);
            return ResponseEntity.ok(connections);
        } catch (Exception e) {
            log.error("Error listing connections: {}", yardId, e);
            throw new SipRccException("Failed to list connections: " + e.getMessage());
        }
    }

    /**
     * Delete a yard
     * DELETE /api/sip/yards/{yardId}
     */
    @DeleteMapping("/yards/{yardId}")
    public ResponseEntity<?> deleteYard(@PathVariable String yardId) {
        log.info("Deleting yard: {}", yardId);
        try {
            yardGraphService.deleteYard(yardId);
            log.info("Yard deleted successfully: {}", yardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting yard: {}", yardId, e);
            throw new SipRccException("Failed to delete yard: " + e.getMessage());
        }
    }

    /**
     * Exception handler for validation errors
     */
    @ExceptionHandler(SipRccException.class)
    public ResponseEntity<?> handleSipRccException(SipRccException ex) {
        log.error("SipRccException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new Object() {
                public int status = 400;
                public String error = "Bad Request";
                public String message = ex.getMessage();
                public long timestamp = System.currentTimeMillis();
            });
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new Object() {
                public int status = 500;
                public String error = "Internal Server Error";
                public String message = "An unexpected error occurred: " + ex.getMessage();
                public long timestamp = System.currentTimeMillis();
            });
    }
}
