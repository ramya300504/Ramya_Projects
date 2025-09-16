package com.hexaware.cozyhaven.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.cozyhaven.config.UserInfoUserDetailsService;
import com.hexaware.cozyhaven.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserInfoUserDetailsService userDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String authHeader = request.getHeader("Authorization");
	    String token = null;
	    String email = null;

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        token = authHeader.substring(7);
	        email = jwtService.extractEmail(token);  // extract email from token
	    }

	    
	    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        
	        
	        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	        
	        
	        if (jwtService.validateToken(token, userDetails)) {
	            
	        	System.out.println(" Token is valid for: " + email);
	            System.out.println(" Authorities: " + userDetails.getAuthorities());

	           
	            UsernamePasswordAuthenticationToken authToken =
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            
	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            
	            
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }
	    filterChain.doFilter(request, response);
	}

}
