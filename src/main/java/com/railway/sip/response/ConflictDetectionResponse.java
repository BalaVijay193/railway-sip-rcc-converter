package com.railway.sip.response;

import com.railway.sip.dto.ConflictDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Response containing detected conflicts
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictDetectionResponse {
    
    private boolean success;
    private String yardId;
    private int totalConflicts;
    private List<ConflictDto> conflicts;
    private long processingTimeMs;
    private String message;
    private long timestamp = System.currentTimeMillis();
}
