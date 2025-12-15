package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccDto {
    private String rccId;
    private String yardId;
    private String yardName;
    private int totalRoutes;
    private List<RccEntryDto> entries;
    private long generatedAt;
	public String getRccId() {
		return rccId;
	}
	public void setRccId(String rccId) {
		this.rccId = rccId;
	}
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
	public int getTotalRoutes() {
		return totalRoutes;
	}
	public void setTotalRoutes(int totalRoutes) {
		this.totalRoutes = totalRoutes;
	}
	public List<RccEntryDto> getEntries() {
		return entries;
	}
	public void setEntries(List<RccEntryDto> entries) {
		this.entries = entries;
	}
	public long getGeneratedAt() {
		return generatedAt;
	}
	public void setGeneratedAt(long generatedAt) {
		this.generatedAt = generatedAt;
	}
}

