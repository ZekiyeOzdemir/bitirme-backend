package com.project.reklamAi.business.concerets;

import com.project.reklamAi.business.abstracts.AdAnalysisService;
import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;
import com.project.reklamAi.core.ModelMapperService;
import com.project.reklamAi.dataAccess.AdAnalysisRepository;
import com.project.reklamAi.entities.AdAnalysis;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdAnalysisServiceImp implements AdAnalysisService {
    AdAnalysisRepository adAnalysisRepository;
    ModelMapperService modelMapperService;
    @Override
    public AdAnalysisResponse createAnalysis(AdAnalysisRequest adAnalysisRequest) {
        AdAnalysis adAnalysis = this.modelMapperService.forRequest().map(adAnalysisRequest, AdAnalysis.class);

        this.adAnalysisRepository.save(adAnalysis);
        return new AdAnalysisResponse("Analiz başarılı bir şekilde oluşturuldu");
    }
}
