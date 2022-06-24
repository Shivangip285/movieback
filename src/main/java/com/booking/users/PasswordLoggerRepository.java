package com.booking.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordLoggerRepository extends JpaRepository<PasswordLogger, Long> {
    @Query(value = "SELECT * FROM PASSWORDLOGGER WHERE USER_ID = :user_id limit(1);", nativeQuery=true)
    Optional<PasswordLogger> findByUser_id(@Param("user_id")  Long user_id);
}
