package com.mdjuniooor.shop.mall.dto;


import com.mdjuniooor.shop.mall.domain.Product;
import com.mdjuniooor.shop.mall.domain.SocialUser;
import com.mdjuniooor.shop.mall.domain.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class SocialQuestionResponseDto {

    private Long id;
    private SocialType socialType;
    private String message;
    private boolean answerState;
    private Integer answerCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private SocialUser socialUser;
    private Product product;
}
