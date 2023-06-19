package com.duan.repositories;

import com.duan.entities.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface SizeDao extends JpaRepository<Size, Integer> {
	@Query("SELECT s FROM Size s WHERE s.name LIKE ?1")
	Page<Size> fillToTable(String name, Pageable pageable);
}
