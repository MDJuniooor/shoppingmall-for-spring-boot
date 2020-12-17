package com.mdjuniooor.shop.mall.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NormalUserResponseDto {

    private Long   id;
    private String identifier;
    private String email;
    private String name;
    private String roadAddr;
    private String buildingName;
    private String detailAddr;
}
