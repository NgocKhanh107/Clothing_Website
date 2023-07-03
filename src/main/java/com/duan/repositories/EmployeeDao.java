package com.duan.repositories;

import com.duan.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

    Page<Employee> findAllBy(Pageable pageable);
}
