package com.hexaware.cozyhaven.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hexaware.cozyhaven.filter.JwtAuthFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	@Autowired
	JwtAuthFilter authFilter;
	
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
		
    	// This returns custom implementation that loads user by email from DB
       return new UserInfoUserDetailsService();
    }
    
    @SuppressWarnings("removal")
	@Bean
    public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
    	
    	return http
    	        .cors()
    	        .and()
    	        .csrf().disable()
    	        .authorizeHttpRequests()
    		   	.requestMatchers(
    					"/user/create/user",
    	                "/user/login/authenticate",
    	                "/user/getbyemail/{email}")
    			.permitAll()
    			.and()
    			.authorizeHttpRequests()
    			.requestMatchers("/admin/**").hasRole("ADMIN")
    			.requestMatchers("/owner/**").hasRole("HOTELOWNER")
    			.requestMatchers("/reviews/**").hasRole("USER")
    			.requestMatchers("/booking/**").hasRole("USER")
    			.requestMatchers("/rooms/**").hasRole("USER")
    			.requestMatchers("/hotels/**").hasRole("USER")
    			.requestMatchers("/user/gettotalprice/{bedType}/{baseFare}/{noOfAdults}/{noOfChildren}").hasRole("USER")
    			.requestMatchers("/user/getreservationbyUser").hasRole("USER")
    			.requestMatchers("/user/processrefund").hasRole("USER")
    			.requestMatchers("/user/updateuser/{userId}").hasRole("USER")
                .anyRequest().authenticated().and()   
    			.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			.and()
    			.authenticationProvider(authenticationProvider())
    			.addFilterBefore(authFilter	, UsernamePasswordAuthenticationFilter.class)
    			.build();
    	
    }
    
    
    
    

    @Bean    
    public PasswordEncoder passwordEncoder() {          
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	
    	return config.getAuthenticationManager();
    	
    }
    
    
    
}
