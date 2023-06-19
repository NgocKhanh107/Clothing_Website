package com.duan.repositories;

import com.duan.entities.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManufactureDao extends JpaRepository<Manufacture, Integer> {
    @Query("SELECT m FROM Manufacture m WHERE m.name LIKE ?1")
    Page<Manufacture> fillToTable(String name, Pageable pageable);
}
