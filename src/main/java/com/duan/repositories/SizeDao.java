package com.duan.repositories;

import com.duan.entities.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface SizeDao extends JpaRepository<Size, Integer> {
	Page<Size> findByNameLike(String name, Pageable pageable);
}
