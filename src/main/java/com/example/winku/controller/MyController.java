package com.example.winku.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
public class MyController {
    @Value("${file.dir}")
    private String fileDir;
    @GetMapping("/inbox")
    public String index(Model model){
        model.addAttribute("name", "suwon");
        return "inbox";
    }
    @ResponseBody
    @GetMapping("/media/{filename}")
    public Resource getMedia(@PathVariable String filename) throws MalformedURLException {
        String fullPath = fileDir + filename;
        return new UrlResource("file:" + fullPath);
    }
}
