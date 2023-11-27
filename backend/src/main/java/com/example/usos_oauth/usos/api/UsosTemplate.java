package com.example.usos_oauth.usos.api;

import com.example.usos_oauth.usos.api.logic.Term;
import com.example.usos_oauth.usos.api.model.*;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class UsosTemplate extends AbstractOAuth1ApiBinding {

    URI courseEditionsUri = URI.create("https://apps.usos.pw.edu.pl/services/courses/user");
    URI userUri = URI.create("https://apps.usos.pw.edu.pl/services/users/user");
    URI userGroups = URI.create("https://apps.usos.pw.edu.pl/services/tt/course_edition");

    public UsosTemplate(String consumerKey, String consumerSecret, String accessToken, String secret) {
        super(consumerKey, consumerSecret, accessToken, secret);
    }

    public List<CourseEdition> getCourseEditions() {
        RestTemplate restTemplate = getRestTemplate();
        CourseResponse response = restTemplate.getForObject(courseEditionsUri, CourseResponse.class);
        return response.getCourse_editions().get(Term.getAcademicTerm());
    }

    public UsosUser getUser() {
        RestTemplate restTemplate = getRestTemplate();
        return restTemplate.getForObject(userUri, UsosUser.class);
    }

    public List<Activity> getUserGroups(Map<String, String> params){
      RestTemplate restTemplate = getRestTemplate();
      URI uri = UriComponentsBuilder.fromUri(userGroups)
        .queryParam("course_id", params.get("course_id"))
        .queryParam("term_id", params.get("term_id"))
        .queryParam("fields", "name|start_time|end_time|course_name|lecturer_ids|classtype_name")
        .build()
        .toUri();
      GroupsResponse response = restTemplate.getForObject(uri, GroupsResponse.class);
      return response.getActivities();
    }
}
