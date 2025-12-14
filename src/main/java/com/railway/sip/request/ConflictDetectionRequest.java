package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Request to detect conflicts between routes
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictDetectionRequest {
    
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotEmpty(message = "At least two route IDs are required")
    private Set<String> routeIds;
    
    @Builder.Default
    private boolean includeDetails = true;
    
    private boolean autoResolve;

	public String getYardId() {
		return yardId;
	}

	public void setYardId(String yardId) {
		this.yardId = yardId;
	}

	public Set<String> getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(Set<String> routeIds) {
		this.routeIds = routeIds;
	}

	public boolean isIncludeDetails() {
		return includeDetails;
	}

	public void setIncludeDetails(boolean includeDetails) {
		this.includeDetails = includeDetails;
	}

	public boolean isAutoResolve() {
		return autoResolve;
	}

	public void setAutoResolve(boolean autoResolve) {
		this.autoResolve = autoResolve;
	}
}
