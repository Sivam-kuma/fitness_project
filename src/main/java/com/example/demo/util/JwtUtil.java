//package com.example.demo.util;
//
//import com.example.demo.security.CustomUserDetails;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
////    public String generateToken(UserDetails userDetails) {
////        return Jwts.builder()
////                .setSubject(userDetails.getUsername())
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
////                .signWith(SignatureAlgorithm.HS512, secret)
////                .compact();
////    }
//
//
////    public String generateToken(UserDetails userDetails, Long userId) {
////        return Jwts.builder()
////                .setSubject(userDetails.getUsername())
////                .claim("userId", userId) // Add userId as a claim
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
////                .signWith(SignatureAlgorithm.HS512, secret)
////                .compact();
////    }
//public String generateToken(UserDetails userDetails) {
//    // Extract userId from CustomUserDetails
//    Long userId = ((CustomUserDetails) userDetails).getUser().getId();
//
//    return Jwts.builder()
//            .setSubject(String.valueOf(userId)) // Set userId as the subject
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//            .signWith(SignatureAlgorithm.HS512, secret)
//            .compact();
//}
//
//
//
//    // Method to extract user ID from the token
//    public Long extractUserId(String token) {
//        return getClaimFromToken(token, claims -> claims.get("user_id", Long.class)); // Extract user_id claim
//    }
//
//    // Utility method to get claims from the token
//    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token); // Extract all claims from the token
//        return claimsResolver.apply(claims); // Apply the function to retrieve specific claim
//    }
//
//    // Method to extract all claims from the token
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); // Parse and retrieve claims
//    }
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String extractUsername(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
////    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = getAllClaimsFromToken(token);
////        return claimsResolver.apply(claims);
////    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = extractExpiration(token);
//        return expiration.before(new Date());
//    }
//}

package com.example.demo.util;

import com.example.demo.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Generate token with userId as the subject
    public String generateToken(UserDetails userDetails, Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // Set userId as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Extract userId from the token
    public Long extractUserId(String token) {
        return Long.valueOf(extractSubject(token)); // UserId is stored as the subject
    }

    // Extract subject (userId) from the token
    public String extractSubject(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final Long userIdFromToken = Long.valueOf(extractSubject(token));
        return (userIdFromToken.equals(((CustomUserDetails) userDetails).getUser().getId()) && !isTokenExpired(token));
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    // Extract expiration date from token
    public Date extractExpiration(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Utility method to get claims from the token
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
