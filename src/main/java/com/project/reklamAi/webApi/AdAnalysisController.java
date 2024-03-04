package com.project.reklamAi.webApi;

import com.project.reklamAi.business.abstracts.AdAnalysisService;
import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/analysis")
@AllArgsConstructor
public class AdAnalysisController {
    private AdAnalysisService adAnalysisService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AdAnalysisResponse createAnalysis(@RequestBody AdAnalysisRequest adAnalysisRequest) {
        return this.adAnalysisService.createAnalysis(adAnalysisRequest);
    }

}
