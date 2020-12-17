package com.mdjuniooor.shop.mall.service;

import com.mdjuniooor.shop.mall.domain.Product;
import com.mdjuniooor.shop.mall.dto.PagingDto;
import com.mdjuniooor.shop.mall.dto.ProductResponseDto;
import com.mdjuniooor.shop.mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // 전체 상품 혹은 카테고리로 상품 조회
    public HashMap<String, Object> getProductListByCategory(String catCd, Pageable pageable, int page) {
       int realPage = page - 1;
       pageable = PageRequest.of(realPage, 9, new Sort(Sort.Direction.DESC, "createdDate"));

        Page<Product> productList;

        if (catCd.equals("ALL")) {
            productList = productRepository.findAll(pageable);
        } else {
            productList = productRepository.findBySmallCatCd(catCd, pageable);
        }

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product : productList) {
            productResponseDtoList.add(product.toResponseDto());
        }

        PageImpl<ProductResponseDto> products = new PageImpl(productResponseDtoList, pageable, productList.getTotalElements());

        PagingDto questionPagingDto = new PagingDto();
        questionPagingDto.setPagingInfo(products);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("productList", products);
        resultMap.put("productPagingDto", questionPagingDto);

        // PageImpl 객체를 반환
        return resultMap;
    }
}
