package com.nisum.apiusers.util;

import com.nisum.apiusers.entities.User;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenUtil {

    public static String generateToken(User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusMinutes(30);
        return Jwts.builder()
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .setSubject(user.getName())
                .setId(user.getIdUser())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

}
