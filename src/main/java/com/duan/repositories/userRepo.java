package com.duan.repositories;



import com.duan.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepo extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.email = :uemail")
    Users findByEmail(@Param("uemail") String email);

    @Query("SELECT u FROM Users u WHERE u.id = :id")
    Users findId(@Param("id") Integer id);

    @Query("SELECT u.fullname FROM Users u WHERE u.email = :uemail")
    String findFullName(@Param("uemail") String email);

    @Query("SELECT u FROM Users u WHERE u.fullname LIKE ?1")
    Page<Users> findByNamePage(String fullname, Pageable pageable);

    @Query("SELECT u.user FROM UserRole u WHERE u.role.id = 2 AND u.user.fullname LIKE ?1")
    Page<Users> fillUser(String fullname, Pageable pageable);
}
