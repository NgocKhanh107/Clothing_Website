package com.duan.repositories;

import java.util.List;

import com.duan.dto.OrderModel;
import com.duan.dto.StatisOrder;
import com.duan.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface OrderDao extends JpaRepository<Order, Integer>{

	List<Order> findByNameLike(String name);
	
	@Query("SELECT new OrderModel(o.name, o.date, sum(o.product.price * o.quality), o.status) FROM Order o " +
			"WHERE o.address.user.id = ?1 GROUP BY o.name, o.date, o.status")
	List<OrderModel> getOrderModel(int id);

	@Query("SELECT new OrderModel(o.name, o.date, sum(o.product.price * o.quality), o.status) " +
			"FROM Order o WHERE o.name LIKE ?1 GROUP BY o.name, o.date, o.status ORDER BY o.date ASC")
	Page<OrderModel> fillTableWOrder2(String name, Pageable pageable);

}
