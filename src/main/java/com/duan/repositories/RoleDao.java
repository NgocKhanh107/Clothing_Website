package com.duan.repositories;

import java.util.List;

import com.duan.entities.Role;
import com.duan.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
