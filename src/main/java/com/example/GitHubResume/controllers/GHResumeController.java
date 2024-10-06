package com.example.GitHubResume.controllers;

import com.example.GitHubResume.entities.GitHubUser;
import com.example.GitHubResume.entities.Repository;
import com.example.GitHubResume.services.LanguagePercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/GH-User")
public class GHResumeController {

    @Autowired
    private LanguagePercentageService languagePercentageService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestParam("account") String username,
                                         @RequestParam(value = "mediaType", defaultValue = "application/json") String mediaType){

        GitHubUser user = new GitHubUser();
        user.setUsername(username);

        //Fetching the user information:
        String userUrl = STR."https://api.github.com/users/\{username}";
        Map<String, Object> userInfo = new RestTemplate().getForObject(userUrl, HashMap.class);

        if(userInfo != null){
            user.setHtmlUrl((String) userInfo.get("html_url"));
        }

        //get the repositories from user account:
        String reposUrl = STR."https://api.github.com/users/\{username}/repos";
        Repository[] repositories = new RestTemplate().getForObject(reposUrl, Repository[].class);

        if(repositories != null){
            List<Repository> repos = Arrays.asList(repositories);
            user.setRepositories(repos);
            Map<String, Double> languagePercentages = languagePercentageService.calculateLanguagePercentages(repos);
            user.setLanguagePercentages(languagePercentages);
        }

        //return the response in the correct format:
        if(mediaType.equals("application/xml")){
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(user);
        } else{
            return ResponseEntity.ok(user);
        }
    }

}
