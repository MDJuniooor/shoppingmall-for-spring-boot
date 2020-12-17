package com.mdjuniooor.shop.mall.dto;

import com.mdjuniooor.shop.mall.domain.enums.SocialType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SocialUserResponseDto {

    private Long id;
    private String name;
    private String email;
    private SocialType socialType;
    private String profileImage;
    private String roadAddr;
    private String buildingName;
    private String detailAddr;
}

