package com.hexaware.cozyhaven.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.AuthenticationRequest;
import com.hexaware.cozyhaven.dto.JwtResponse;
import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Refund;
import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;
import com.hexaware.cozyhaven.service.IUserService;
import com.hexaware.cozyhaven.service.JWTService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/create/user")
	public User createUser(@RequestBody @Valid UserDTO userdto) {
		
		log.info("User created Successfully");
	    return	userService.createUser(userdto);
	    
	}
	
	@GetMapping("/getbyemail/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.findByEmail(email);
    }
	
	@PutMapping("/updateuser/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserDTO updateUser(@RequestBody UserDTO userdto,@PathVariable Integer userId) throws UserNotFoundException{
		
		return userService.updateUser(userdto, userId);
		
	}
	
	@GetMapping("/getreservationbyUser/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	List<ReservationsDTO>getReservatiosByUser(@PathVariable Integer userId){
		
		return userService.getReservatiosByUser(userId);
	}
	
	@PostMapping("/login/authenticate")
	public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody @Valid AuthenticationRequest authRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

	    log.info("Authentication: {}", authentication.isAuthenticated());
	    String token = null;

	    if (authentication.isAuthenticated())
	    {
	    	
	    	// Get the authenticated user's details
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        // Generate token including authorities-admin,owner,user
	        token = jwtService.generateToken(userDetails);
	        log.info("Generated Token: {}", token);
	        log.info("Login Attempt: {}", authRequest.getEmail());
	        return ResponseEntity.ok(new JwtResponse(token));
	    } else {
	    	
	        log.info("Invalid email or password");
	        throw new UsernameNotFoundException("Email or Password is Invalid");
	    }
	}
	
	
	@PostMapping("/processrefund")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Refund processRefund(@RequestBody RefundDTO refunddto) {
		
		return userService.processRefund(refunddto);
		
	}
	
	@GetMapping("/gettotalprice/{bedType}/{baseFare}/{noOfAdults}/{noOfChildren}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Double calculateTotalPrice(@PathVariable String bedType,@PathVariable double baseFare,@PathVariable Integer noOfAdults,@PathVariable Integer noOfChildren) {
		
		
		return userService.calculateTotalPrice(bedType, baseFare, noOfAdults,noOfChildren);
	}
         
	}
