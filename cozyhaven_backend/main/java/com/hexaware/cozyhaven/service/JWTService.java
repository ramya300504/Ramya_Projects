package com.hexaware.cozyhaven.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service
public class JWTService {
	
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	// BELOW METHODS GENERATE AND GIVE TOKEN
	
	public String createToken(Map<String, Object> claims, String email) {

		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

	}
	
	private Key getSignKey() {

		byte[] keyBytes = Decoders.BASE64.decode(SECRET);

		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken( UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
	    
	    // Add authorities to token claims
	    claims.put("authorities", userDetails.getAuthorities().stream()
	        .map(GrantedAuthority::getAuthority)
	        .collect(Collectors.toList()));
	    
	    return createToken(claims, userDetails.getUsername());
	    
	}
	
	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		

		return claimsResolver.apply(claims);

	}

	 public String extractEmail(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	 public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	
	 private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	 public Boolean validateToken(String token, UserDetails userDetails) {
	        final String email = extractEmail(token);
	        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    } 


}
