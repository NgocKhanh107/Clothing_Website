package com.duan.repositories;


import com.duan.entities.ProductCate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCateDao extends JpaRepository<ProductCate,Integer> {
    @Query("SELECT new ProductCate (p.category, count(p.product)) FROM ProductCate p GROUP BY p.category")
    List<ProductCate> getSelectCategory();
}
