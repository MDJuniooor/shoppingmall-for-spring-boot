package com.mdjuniooor.shop.mall.repository;


import com.mdjuniooor.shop.mall.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findBySmallCatCd(String catCd, Pageable pageable);
}
