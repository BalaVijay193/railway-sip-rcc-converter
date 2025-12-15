package com.railway.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RccDetailDto {
    private String rccId;
    private String yardId;
    private List<String> selectedRoutes;
    private List<String> conflicts;
    private List<String> overlaps;
    private List<String> isolationPoints;
    private String exportFormat;
    private String status;
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
	public List<String> getSelectedRoutes() {
		return selectedRoutes;
	}
	public void setSelectedRoutes(List<String> selectedRoutes) {
		this.selectedRoutes = selectedRoutes;
	}
	public List<String> getConflicts() {
		return conflicts;
	}
	public void setConflicts(List<String> conflicts) {
		this.conflicts = conflicts;
	}
	public List<String> getOverlaps() {
		return overlaps;
	}
	public void setOverlaps(List<String> overlaps) {
		this.overlaps = overlaps;
	}
	public List<String> getIsolationPoints() {
		return isolationPoints;
	}
	public void setIsolationPoints(List<String> isolationPoints) {
		this.isolationPoints = isolationPoints;
	}
	public String getExportFormat() {
		return exportFormat;
	}
	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
