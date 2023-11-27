package com.example.usos_oauth.usos.controller;

import com.example.usos_oauth.security.service.UserService;
import com.example.usos_oauth.usos.api.UsosTemplate;
import com.example.usos_oauth.usos.api.model.CourseEdition;
import com.example.usos_oauth.usos.connect.UsosServiceProvider;
import lombok.AllArgsConstructor;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/course-editions")
public class CourseEditionsController {

    private UsosServiceProvider usosServiceProvider;
    private UserService userService;

    @GetMapping
    public List<CourseEdition> getCourseEditions() {
        OAuthToken token = userService.getCurrentUserToken();
        UsosTemplate api = usosServiceProvider.getApi(token);
        return api.getCourseEditions();
    }
}
