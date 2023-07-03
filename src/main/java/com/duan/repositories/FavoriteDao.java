package com.duan.repositories;

import java.util.List;

import com.duan.dto.StatisFavorite;
import com.duan.entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FavoriteDao extends JpaRepository<Favorite, Integer> {

	Integer countFavoritesByUser_Id(Integer id);
	
	Favorite findByUser_EmailAndProduct_Id(String email,int id);

	List<Favorite> findByUser_EmailLike(String email);

}
