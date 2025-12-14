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
public class AddSignalRequest {
    @NotBlank(message = "Yard ID is required")
    private String yardId;
    
    @NotBlank(message = "Signal ID is required")
    private String signalId;
    
    @NotBlank(message = "Signal name is required")
    private String signalName;
    
    @NotBlank(message = "Signal type is required")
    private String signalType;
    
    private double positionX;
    private double positionY;
	public String getYardId() {
		return yardId;
	}
	public void setYardId(String yardId) {
		this.yardId = yardId;
	}
	public String getSignalId() {
		return signalId;
	}
	public void setSignalId(String signalId) {
		this.signalId = signalId;
	}
	public String getSignalName() {
		return signalName;
	}
	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}
	public String getSignalType() {
		return signalType;
	}
	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
}
