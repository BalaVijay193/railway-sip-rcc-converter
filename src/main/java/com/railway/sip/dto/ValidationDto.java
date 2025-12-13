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
}
