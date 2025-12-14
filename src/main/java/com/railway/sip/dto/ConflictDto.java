package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictDto {
    private String route1Id;
    private String route2Id;
    private String type;
    private String severity;
    private String description;
	public String getRoute1Id() {
		return route1Id;
	}
	public void setRoute1Id(String route1Id) {
		this.route1Id = route1Id;
	}
	public String getRoute2Id() {
		return route2Id;
	}
	public void setRoute2Id(String route2Id) {
		this.route2Id = route2Id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
