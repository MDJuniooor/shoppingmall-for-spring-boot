package com.mdjuniooor.shop.mall.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class ProductController {

    @GetMapping("/productlist")
    public String initProductList(Model model) {

        model.addAttribute("pageName", "productList");

        return "product/productList";
    }
}
