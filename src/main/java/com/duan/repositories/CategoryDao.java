package com.duan.repositories;

import com.duan.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryDao extends JpaRepository<Category, Integer> {

	Page<Category> findByNameLike(String name, Pageable pageable);

}
