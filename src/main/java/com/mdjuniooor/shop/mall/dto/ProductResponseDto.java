package com.mdjuniooor.shop.mall.dto;

import com.mdjuniooor.shop.mall.domain.ProductImg;
import com.mdjuniooor.shop.mall.domain.Question;
import com.mdjuniooor.shop.mall.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ProductResponseDto {

    private Long id;
    private String productNm;
    private String titleImg;
    private String largeCatCd;
    private String smallCatCd;
    private Integer price;
    private Integer purchaseCount;
    private Integer limitCount;
    private Integer totalCount;
    private ProductStatus productStatus;
    private List<ProductImg> productImg;
    private List<Question> questions;
}

