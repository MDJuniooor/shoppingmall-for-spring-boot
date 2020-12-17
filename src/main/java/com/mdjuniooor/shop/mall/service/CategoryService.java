package com.mdjuniooor.shop.mall.service;

import com.mdjuniooor.shop.mall.domain.ProductCat;
import com.mdjuniooor.shop.mall.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<ProductCat> getCategoryList() {

        return categoryRepository.findAll();
    }
}
