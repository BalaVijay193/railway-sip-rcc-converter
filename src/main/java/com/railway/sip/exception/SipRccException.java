package com.railway.sip.exception;

public class SipRccException extends RuntimeException {
    
    public SipRccException(String message) {
        super(message);
    }
    
    public SipRccException(String message, Throwable cause) {
        super(message, cause);
    }
}
