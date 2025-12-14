package com.railway.sip.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateYardRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Yard name is required")
    private String yardName;
    
    private String description;
    private String location;
	public String getYardId() {
		return yardId;
	}
	public void setYardId(String yardId) {
		this.yardId = yardId;
	}
	public String getYardName() {
		return yardName;
	}
	public void setYardName(String yardName) {
		this.yardName = yardName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
