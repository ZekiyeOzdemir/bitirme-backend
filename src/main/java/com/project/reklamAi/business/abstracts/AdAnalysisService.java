package com.project.reklamAi.business.abstracts;

import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;

public interface AdAnalysisService {
    public AdAnalysisResponse createAnalysis(AdAnalysisRequest adAnalysisRequest);
}
