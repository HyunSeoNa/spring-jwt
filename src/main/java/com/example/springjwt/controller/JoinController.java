package com.example.springjwt.controller;

import com.example.springjwt.dto.JoinDTO;
import com.example.springjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * fileName     : null.java
 * author       : hyunseo
 * date         : 2025. 3. 19.
 * description  :
 */
@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);
        
        return "OK";
    }
}
