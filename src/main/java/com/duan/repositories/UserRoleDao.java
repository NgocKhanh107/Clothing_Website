package com.duan.repositories;

import com.duan.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
	Integer findByUser_Id(Integer id);
}
