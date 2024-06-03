package com.test.parsor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParsorController {

    /**
     * 메인 페이지 포워딩
     * @return "index" : 메인 페이지
     */
    @GetMapping("/index.kr")
    public String index() {
        return "index";
    }

    @PostMapping("/view.kr")
    public String view() {
        return "view";
    }

}//End of class
