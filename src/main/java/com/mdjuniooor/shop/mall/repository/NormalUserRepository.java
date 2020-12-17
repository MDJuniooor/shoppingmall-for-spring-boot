package com.mdjuniooor.shop.mall.repository;

import com.mdjuniooor.shop.mall.domain.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {
    Optional<NormalUser> findByIdentifier(String identifier);
}

