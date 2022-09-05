package com.example.elections.repository;

import com.example.elections.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(value = "SELECT * FROM Reset_Password u WHERE u.token= :token", nativeQuery = true)
    PasswordResetToken findByToken(String token);

}
