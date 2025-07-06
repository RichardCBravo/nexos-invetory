package com.nexos.inventory.persistence.repository;

import com.nexos.inventory.persistence.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    boolean existsByProductName(String productName);
    boolean existsByProductNameAndIdNot(String productName, Long id);
}
