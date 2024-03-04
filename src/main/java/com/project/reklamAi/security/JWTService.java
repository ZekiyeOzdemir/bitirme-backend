package com.project.reklamAi.security;

import com.project.reklamAi.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    //JWT Token Provider
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    private String generateToken(Map<String, Object> extraClaims, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("uploadedAudios", user.getUploadedAudios());

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername()) //subject is user email
                .setIssuedAt(new Date(System.currentTimeMillis())) //if token is valid or not
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //to declare which key we should to use sign this token
                .compact(); //compact is generate and return the token.
    }

    public boolean isTokenValid(String token, UserDetails userDetails) { //validate to if this token belongs the this userDetails
        final String username = extractUserName(token); //username is email here
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //for get the username, will return email
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject); //subject is email of user
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimResolvers.apply(claims);
    }

    private SecretKey getSignInKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
