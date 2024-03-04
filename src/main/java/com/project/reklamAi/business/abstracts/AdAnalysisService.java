package com.project.reklamAi.business.abstracts;

import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;
import com.project.reklamAi.business.responses.GetAllByIdAdAnalysisResponse;

import java.util.List;

public interface AdAnalysisService {
    public AdAnalysisResponse createAnalysis(AdAnalysisRequest adAnalysisRequest);

    public List<GetAllByIdAdAnalysisResponse> getByIdAdAnalysis(String userId);
}
