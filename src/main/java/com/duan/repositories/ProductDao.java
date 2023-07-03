package com.duan.repositories;

import java.util.List;

import com.duan.entities.Manufacture;
import com.duan.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProductDao extends JpaRepository<Product, Integer> {

	Page<Product> findByNameLike(String name, Pageable pageable);

	@Query(value="select * from Products where id not in" +
			" (select p.Product_Id from Product_Cate p inner join Categories c on p.Cate_Id = c.id WHERE c.id = :uid)", nativeQuery = true)
	List<Product> findProduct(@Param("uid") int id);
	
	@Query(value="select * from Products where id not in" +
			" (select p.Product_Id from Product_Size p inner join Sizes c on p.Size_Id = c.id WHERE c.id = :uid)", nativeQuery = true)
	List<Product> findSize(@Param("uid") int id);
	
	@Query(value="select * from Products where id not in " +
			"(select p.Product_Id from Product_Color p inner join Colors c on p.Color_Id = c.id WHERE c.id = :uid)", nativeQuery = true)
	List<Product> findColor(@Param("uid") int id);

	List<Product> findTop8ByStatusOrderByViewsDesc(boolean status);

	List<Product> findTop8ByStatusOrderByManuDayDesc(boolean status);

	@Query(value="SELECT * FROM Products WHERE Status = 1 AND Id In " +
			"(SELECT Product_Id From Product_Cate WHERE Cate_Id = ?1) order by ManuDay DESC", nativeQuery = true)
	Page<Product> fillShopDESC(int number1 , Pageable pageable);

	List<Product> findByManufacture_Id(int id);
}
