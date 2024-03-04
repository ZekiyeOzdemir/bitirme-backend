package com.project.reklamAi.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdAnalysisRequest {
    private String userIdentifier;
    private String copy;
    private String productType;
    private List<String> audience;
    private List<String> gender;
    private List<String> result;
}
