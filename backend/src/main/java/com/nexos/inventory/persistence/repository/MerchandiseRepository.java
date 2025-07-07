package com.nexos.inventory.persistence.repository;

import com.nexos.inventory.persistence.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    boolean existsByProductName(String productName);

    boolean existsByProductNameAndIdNot(String productName, Long id);

    @Query("""
            SELECT m FROM Merchandise m
            WHERE (:productName IS NULL OR LOWER(m.productName) LIKE LOWER(CONCAT('%', :productName, '%')))
            AND (:userId IS NULL OR m.createdByUser.id = :userId)
            """)
    List<Merchandise> findBySearch(@Param("productName") String productName, @Param("userId") Long userId);
}
