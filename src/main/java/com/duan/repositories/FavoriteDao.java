package com.duan.repositories;

import java.util.List;

import com.duan.dto.StatisFavorite;
import com.duan.entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FavoriteDao extends JpaRepository<Favorite, Integer> {
	@Query(value = "select count(*) from Favorites where User_Id = ?1", nativeQuery = true)
	int countFavorite(int id);
	
	@Query("SELECT f FROM Favorite f WHERE f.user.email like ?1 AND f.product.id = ?2")
	Favorite getFavorite(String id1, int id2);
	
	@Query("SELECT f FROM Favorite f WHERE f.user.email like ?1")
	List<Favorite> getFavoriteByEmail(String id);
	
//	@Query("SELECT new StatisFavorite(f.product, count(f)) FROM Favorite f WHERE f.product.name Like ?1 GROUP BY f.product ")
//	Page<StatisFavorite> fillToTable(String name, Pageable pageable);
}
