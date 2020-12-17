package com.mdjuniooor.shop.mall.restcontroller;

import com.mdjuniooor.shop.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/productList/{page}/catCd/{cat-cd}")
    public ResponseEntity<?> getProductList(@PathVariable("page") int page, @PathVariable("cat-cd") String catCd,
                                            @PageableDefault(size = 9, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(productService.getProductListByCategory(catCd, pageable, page));
    }
}
