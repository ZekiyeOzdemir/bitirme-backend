package com.project.reklamAi.webApi;

import com.project.reklamAi.business.abstracts.AdAnalysisService;
import com.project.reklamAi.business.requests.AdAnalysisRequest;
import com.project.reklamAi.business.responses.AdAnalysisResponse;
import com.project.reklamAi.business.responses.GetAllByIdAdAnalysisResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/analysis")
@AllArgsConstructor
public class AdAnalysisController {
    private AdAnalysisService adAnalysisService;

    @PostMapping
    @CrossOrigin(origins = { "*" })
    @ResponseStatus(code = HttpStatus.CREATED)
    public AdAnalysisResponse createAnalysis(@RequestBody AdAnalysisRequest adAnalysisRequest) {
        return this.adAnalysisService.createAnalysis(adAnalysisRequest);
    }

    @GetMapping("/getAll/{userId}")
    @CrossOrigin(origins = { "*" })
    public List<GetAllByIdAdAnalysisResponse> getByIdAdAnalysis(@PathVariable String userId) {
        return adAnalysisService.getByIdAdAnalysis(userId);
    }

}
