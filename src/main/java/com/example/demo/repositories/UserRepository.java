package com.example.demo.repositories;

import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username=:usernameOrEmail OR u.email=:usernameOrEmail")
    User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u from User u where u.confirmationToken=:confirmationToken")
    User findByConfirmationToken(@Param("confirmationToken") String confirmationToken);
}

