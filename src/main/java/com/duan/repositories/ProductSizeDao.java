package com.duan.repositories;

import java.util.List;

import com.duan.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.duan.dto.ShowSize;


public interface ProductSizeDao extends JpaRepository<ProductSize, Integer> {
	@Query("SELECT new ProductSize (p.size) FROM ProductSize p WHERE p.product.id IN (SELECT pc.product.id FROM ProductCate pc WHERE pc.category.id = ?1) GROUP BY p.size")
	List<ProductSize> getSize(int number);
	@Query("select a.size,count(a.product) from ProductSize a where a.product.id in (select pc.product.id from ProductCate pc where  pc.category.id =: id) group by a.size ")
	List<ProductSize> getSelectSize(int number);
	@Query("SELECT p FROM ProductSize p WHERE p.product.id = ?1")
	List<ProductSize> getSizeProduct(int number);
}
