package com.duan.repositories;

import com.duan.entities.ProductColor;
import com.duan.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductColorDao extends JpaRepository<ProductColor,Integer> {
    @Query("SELECT new ProductColor (p.color) FROM ProductColor p WHERE p.product.id " +
            "IN (SELECT pc.product.id FROM ProductCate pc WHERE pc.category.id = ?1) GROUP BY p.color")
    List<ProductColor> getColor(int number);

    List<ProductColor> findByProduct_Id(Integer id);

}
