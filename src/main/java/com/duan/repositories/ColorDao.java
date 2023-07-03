package com.duan.repositories;

import com.duan.entities.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ColorDao extends JpaRepository<Color, Integer> {

	Page<Color> findByNameLike(String name, Pageable pageable);

}
