package com.example.GitHubResume.entities;


import java.util.List;
import java.util.Map;


public class GitHubUser {

    private String username;
    private String htmlUrl;
    private List<Repository> repositories;
    private Map<String, Double> languagePercentages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public Map<String, Double> getLanguagePercentages() {
        return languagePercentages;
    }

    public void setLanguagePercentages(Map<String, Double> languagePercentages) {
        this.languagePercentages = languagePercentages;
    }
}
