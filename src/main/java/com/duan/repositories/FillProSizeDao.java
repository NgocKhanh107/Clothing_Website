package com.duan.repositories;

import com.duan.dto.FillProSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FillProSizeDao extends JpaRepository<FillProSize, Integer> {
	@Query("SELECT new FillProSize(p.id, p.product.name, p.size.name) FROM ProductSize p WHERE p.size.name LIKE ?1")
	Page<FillProSize> fillToTable(String name, Pageable pageable);
}
