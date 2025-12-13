package com.railway.sip.util;

import com.railway.sip.graph.YardNode;

public class SignalUtil {
    
    public static boolean isMainSignal(YardNode signal) {
        return signal.getSignalType() == YardNode.SignalType.HOME ||
               signal.getSignalType() == YardNode.SignalType.STARTER ||
               signal.getSignalType() == YardNode.SignalType.ADVANCED_STARTER;
    }
    
    public static boolean isShuntSignal(YardNode signal) {
        return signal.getSignalType() == YardNode.SignalType.DEPENDENT_SHUNT ||
               signal.getSignalType() == YardNode.SignalType.INDEPENDENT_SHUNT;
    }
    
    public static int getDefaultOverlapLength(YardNode signal) {
        if (signal.getSignalType() == YardNode.SignalType.HOME) {
            return 75;  // Home signal requires longer overlap
        } else if (signal.getSignalType() == YardNode.SignalType.STARTER) {
            return 50;
        }
        return 25;
    }
}
