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
public class ValidationDto {
    private String yardId;
    private boolean valid;
    private List<String> errors;
    private List<String> warnings;
    private int totalElements;
    private String status;
	public String getYardId() {
		return yardId;
	}
	public void setYardId(String yardId) {
		this.yardId = yardId;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public List<String> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
