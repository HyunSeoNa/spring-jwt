package com.example.springjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * fileName     : AdminController.java
 * author       : hyunseo
 * date         : 2025. 3. 19.
 * description  :
 */
@Controller
@ResponseBody
public class AdminController {
    @GetMapping("/admin")
    public String adminP() {
        return "Admin Controller";
    }
}
