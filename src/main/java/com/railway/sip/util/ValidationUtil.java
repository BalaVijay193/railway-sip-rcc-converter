package com.railway.sip.util;

import com.railway.sip.model.Route;
import java.util.Set;

public class ValidationUtil {
    
    public static boolean isValidRoute(Route route) {
        return route != null &&
               route.getEntrySignalId() != null && !route.getEntrySignalId().isEmpty() &&
               route.getExitSignalId() != null && !route.getExitSignalId().isEmpty() &&
               route.getControlTrackIds() != null && !route.getControlTrackIds().isEmpty();
    }
    
    public static boolean hasConflict(Route r1, Route r2) {
        if (r1 == null || r2 == null) return false;
        
        // Check common tracks
        Set<String> common = new java.util.HashSet<>(r1.getControlTrackIds());
        common.retainAll(r2.getControlTrackIds());
        
        return !common.isEmpty();
    }
    
    public static boolean validateCoordinates(double x1, double y1, double x2, double y2) {
        return x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0 && x1 <= x2;
    }
}
