package com.mdjuniooor.shop.mall.domain;

import com.mdjuniooor.shop.mall.domain.enums.ProductStatus;
import com.mdjuniooor.shop.mall.dto.ProductResponseDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String productNm;

    @Column(length = 10)
    private String largeCatCd;

    @Column(length = 10)
    private String smallCatCd;

    @Column
    private Integer price;

    @Column
    private Integer purchaseCount;

    @Column
    private Integer limitCount;

    @Column
    private Integer totalCount;

    @Column(length = 10)
    private ProductStatus productStatus;

    @Column(length = 100)
    private String titleImg;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductImg productImg;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDisPrc productDisPrc;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SocialQuestion> socialQuestions;

    public ProductResponseDto toResponseDto() {

        return ProductResponseDto.builder()
                .id(id)
                .productNm(productNm)
                .largeCatCd(largeCatCd)
                .smallCatCd(smallCatCd)
                .price(price)
                .purchaseCount(purchaseCount)
                .limitCount(limitCount)
                .totalCount(totalCount)
                .productStatus(productStatus)
                .titleImg(titleImg)
                .questions(questions)
                .build();
    }
}

