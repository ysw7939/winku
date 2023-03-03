package com.example.winku.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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

    @Value("${file.repository}")
    private String fileRepository;

    @GetMapping("/inbox")
    public String index(Model model){
        model.addAttribute("name", "suwon");
        return "inbox";
    }
/*    @ResponseBody // html소스파일이 아닌 일반 데이터로
    @GetMapping("/media/{filename}")
    public Resource getMedia(@PathVariable String filename) throws MalformedURLException {
        String fullPath = fileDir + filename;
        return new UrlResource("file:" + fullPath);
    }*/

    @ResponseBody
    @GetMapping("/media/resources/{filename}")
    public Resource getImg(@PathVariable String filename) throws MalformedURLException {
        String fullPath = fileRepository + filename;
        System.out.println(fullPath);
        return new UrlResource("file:" + fullPath);
    }


}
