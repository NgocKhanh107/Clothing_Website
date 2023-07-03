package com.duan.repositories;



import com.duan.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface userRepo extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Optional<Users> findById(Integer id);

    String findFullNameByEmail(String email);

    Page<Users> findByFullnameLike(String fullname, Pageable pageable);

    @Query("SELECT u.user FROM UserRole u WHERE u.role.id = 2 AND u.user.fullname LIKE ?1")
    Page<Users> findByRole_IdAndUser_Fullname(int id,String fullname, Pageable pageable);


}
