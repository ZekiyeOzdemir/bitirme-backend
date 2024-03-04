package com.project.reklamAi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "adAnalysis")
@AllArgsConstructor
@NoArgsConstructor
public class AdAnalysis {
    @Id
    private String id;
    private String userId;
    private String copy;
    private String productType;
    private List<String> audience;
    private List<String> gender;
    private List<String> result;
}
