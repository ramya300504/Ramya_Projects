package com.hexaware.cozyhaven.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
	
	@Email(message = "Example format: ramya@gmail.com")
	@NotNull(message = "Email Should not be Null")
	private String email;
	
	@Size(min = 6)
    @NotNull(message = "Password should not be null")
    private String password;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
