package com.mdjuniooor.shop.mall.dto;

import com.mdjuniooor.shop.mall.domain.NormalUser;
import com.mdjuniooor.shop.mall.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class QuestionResponseDto {

    private Long id;
    private String message;
    private boolean answerState;
    private Integer answerCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private NormalUser normalUser;
    private Product product;
}

