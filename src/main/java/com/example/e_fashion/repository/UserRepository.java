package com.example.e_fashion.repository;

import com.example.e_fashion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("""
            UPDATE User u
            SET u.email = :#{#user.email}, u.firstName = :#{#user.firstName}, u.lastName = :#{#user.lastName}, u.gender = :#{#user.gender},
                u.phone = :#{#user.phone}, u.address = :#{#user.address}, u.city = :#{#user.city}, u.dob = :#{#user.dob}
            WHERE u.id = :#{#user.id}
            """)
    void updateUserById(@Param("user") User user);

    @Modifying
    @Transactional
    @Query(" UPDATE User u SET u.password = :password WHERE u.id = :id")
    void changePassword(@Param("id") String id, @Param("password") String newPassword);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    String findIdByEmail(@Param("email") String email);
}
