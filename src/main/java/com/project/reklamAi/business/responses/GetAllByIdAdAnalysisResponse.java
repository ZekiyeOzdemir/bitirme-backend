package com.project.reklamAi.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByIdAdAnalysisResponse {
    private String id;
    private String userId;
    private String copy;
    private String productType;
    private List<String> audience;
    private List<String> gender;
    private List<String> result;
}
