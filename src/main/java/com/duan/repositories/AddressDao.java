package com.duan.repositories;

import com.duan.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AddressDao extends JpaRepository<Address, Integer> {
	Address findByUser_Id(Integer id);
}
