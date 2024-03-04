package com.project.reklamAi.business.concerets;

import com.project.reklamAi.business.abstracts.AdAnalysisService;
import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;
import com.project.reklamAi.business.responses.GetAllByIdAdAnalysisResponse;
import com.project.reklamAi.core.ModelMapperService;
import com.project.reklamAi.dataAccess.AdAnalysisRepository;
import com.project.reklamAi.entities.AdAnalysis;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

      @Override
    public List<GetAllByIdAdAnalysisResponse> getByIdAdAnalysis(String userId) {
        List<AdAnalysis> adAnalysisList = adAnalysisRepository.findAllByUserIdentifier(userId);

        List<GetAllByIdAdAnalysisResponse> getAllByIdAdAnalysisResponse = adAnalysisList.stream().map(adAnalysisListItem -> this.modelMapperService.forResponse().map(adAnalysisListItem, GetAllByIdAdAnalysisResponse.class)).collect(Collectors.toList());
        return getAllByIdAdAnalysisResponse;
    }
}
