package com.dorta.seguros.seguros.security;

import com.dorta.seguros.seguros.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =  "3d3f5e8a9c7b1d2f4e6a8c0b7f9d2a1e5c3b6f7a8d9e0c1b2f4d6a7c8e9b0d1f";

    public JwtService() {
    }


    public String generateToken(Usuario user){
        long expirationTime = 1000 * 60 * 60 * 24; //24horas
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +  expirationTime);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            // aquí puedes loggear el error o manejarlo
            return null; // o lanzar excepción personalizada
        }
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final var claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        System.out.println("Extracted username from token: " + username);
        System.out.println("UserDetails username: " + userDetails.getUsername());
        System.out.println("Token expired? " + isTokenExpired(token));
        return (username != null && username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

}
