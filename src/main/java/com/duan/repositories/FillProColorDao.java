package com.duan.repositories;

import com.duan.dto.FillProColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FillProColorDao extends JpaRepository<FillProColor, Integer> {
	@Query("SELECT new FillProColor(p.id, p.image, p.product.name, p.color.name) FROM ProductColor p WHERE p.color.name LIKE ?1")
	Page<FillProColor> fillToTable(String name, Pageable pageable);
}
