package com.railway.sip.integration;

import com.railway.sip.SipRccConverterApplication;
import com.railway.sip.dto.RouteDto;
import com.railway.sip.request.RouteEnumerationRequest;
import com.railway.sip.response.RouteEnumerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = SipRccConverterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SipRccIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testApplicationContextLoads() {
        assertNotNull(restTemplate);
    }
    
    @Test
    public void testHealthEndpoint() {
        ResponseEntity<Object> response = restTemplate.getForEntity("/railway-sip-rcc/api/sip/health", Object.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info("Health check passed");
    }
    
    @Test
    public void testCreateYard() {
        String url = "/railway-sip-rcc/api/sip/yards";
        
        ResponseEntity<Object> response = restTemplate.postForEntity(url, 
            new Object() {
                public String yardId = "MARKONA_001";
                public String yardName = "Markona Railway Yard";
            }, 
            Object.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info("Yard creation successful");
    }
}
