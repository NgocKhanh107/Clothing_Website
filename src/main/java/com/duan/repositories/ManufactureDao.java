package com.duan.repositories;

import com.duan.entities.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufactureDao extends JpaRepository<Manufacture, Integer> {

    Page<Manufacture> findByNameLike(String name, Pageable pageable);


}
