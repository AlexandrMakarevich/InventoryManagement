package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("runApplicationController")
public class RunApplicationController {
    
    @RequestMapping(value = "/")
    public String run() {
        return"welcome_page";
    }

    @RequestMapping(value = "/about_application")
    public String runAbout() {
        return "about_application";
    }
}
