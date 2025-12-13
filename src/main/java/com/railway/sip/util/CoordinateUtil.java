package com.railway.sip.util;

public class CoordinateUtil {
    
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
    
    public static boolean isWithinProximity(double x1, double y1, double x2, double y2, double threshold) {
        return calculateDistance(x1, y1, x2, y2) <= threshold;
    }
    
    public static boolean isHorizontallyAligned(double y1, double y2, double tolerance) {
        return Math.abs(y1 - y2) <= tolerance;
    }
    
    public static double getMidpoint(double x1, double x2) {
        return (x1 + x2) / 2.0;
    }
}
