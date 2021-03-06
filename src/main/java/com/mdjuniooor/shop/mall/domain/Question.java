package com.mdjuniooor.shop.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdjuniooor.shop.mall.dto.QuestionResponseDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "question")
@EntityListeners(value = {AuditingEntityListener.class})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean answerState;
    private Integer answerCount;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    // 객체들 간의 관계
    @ManyToOne
    @JoinColumn(name = "normalUser_id", referencedColumnName = "id")
    private NormalUser normalUser;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    public QuestionResponseDto toResponseDto() {

        return QuestionResponseDto.builder()
                .id(id)
                .normalUser(normalUser)
                .product(product)
                .message(message)
                .answerCount(answerCount)
                .answerState(answerState)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}

