package com.mdjuniooor.shop.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String initMain(){
        return "main/main";
    }
}
