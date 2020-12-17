package com.mdjuniooor.shop.mall.repository;

import com.mdjuniooor.shop.mall.domain.ProductCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<ProductCat, Long> {
}