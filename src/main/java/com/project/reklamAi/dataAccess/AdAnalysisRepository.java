package com.project.reklamAi.dataAccess;

import com.project.reklamAi.entities.AdAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdAnalysisRepository extends MongoRepository<AdAnalysis, String> {
}
