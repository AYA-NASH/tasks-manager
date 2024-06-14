package com.ropulva.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping("/login/oauth2/code/google")
    public String googleCallback() {
        return "redirect:/";
    }
}
