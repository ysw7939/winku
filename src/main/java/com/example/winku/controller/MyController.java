package com.example.winku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MyController {

    @GetMapping("/inbox")
    public String index(Model model){
        model.addAttribute("name", "suwon");
        return "inbox";
    }
}
