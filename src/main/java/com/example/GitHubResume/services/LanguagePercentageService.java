package com.example.GitHubResume.services;

import com.example.GitHubResume.entities.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LanguagePercentageService {

    public Map<String, Double> calculateLanguagePercentages(List<Repository> repositories) {
        Map<String, Double> languageSizes = new HashMap<>();
        double totalSize = 0;

        for (Repository repo: repositories){
            String language = repo.getLanguage();
            double size = repo.getSize();

            if(language != null) {
                totalSize += size;
                languageSizes.put(language, languageSizes.getOrDefault(language, 0.0) + size);
            }
        }

        //Calculate percentages:
        Map<String, Double> percentages = new HashMap<>();
        for(Map.Entry<String, Double> entry : languageSizes.entrySet()){
            String language = entry.getKey();
            double size = entry.getValue();
            double percentage = (size/totalSize) * 100;
            percentages.put(language, percentage);
        }

        return percentages;
    }
}
