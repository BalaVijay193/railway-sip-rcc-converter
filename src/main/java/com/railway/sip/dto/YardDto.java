package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardDto {
    private String yardId;
    private String yardName;
    private String description;
    private String location;
    private int trackCount;
    private int signalCount;
    private int pointCount;
    private String status;
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
	public int getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getPointCount() {
		return pointCount;
	}
	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
