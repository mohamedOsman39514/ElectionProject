package com.example.elections.service;

import com.example.elections.model.PasswordResetToken;
import com.example.elections.model.Voter;
import com.example.elections.repository.PasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class PasswordTokenService {

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Value("${jwt.resetTokenExpiration}")
    private int resetTokenExpiration;

    public void createPasswordResetTokenForUser(Voter user, String token) {

        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setVoter(user);
        myToken.setToken(token);
        myToken.setExpiryDate(new Date(System.currentTimeMillis() + resetTokenExpiration));
        passwordTokenRepository.save(myToken);
    }

    public PasswordResetToken getResetToken(String Token) {
        return passwordTokenRepository.findByToken(Token);
    }
}
