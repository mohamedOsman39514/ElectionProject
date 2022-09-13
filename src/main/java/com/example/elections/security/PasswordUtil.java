package com.example.elections.security;

import com.example.elections.model.PasswordResetToken;
import com.example.elections.service.PasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
@Component
public class PasswordUtil {

    @Autowired
    private PasswordTokenService passwordTokenService;

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenService.getResetToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
