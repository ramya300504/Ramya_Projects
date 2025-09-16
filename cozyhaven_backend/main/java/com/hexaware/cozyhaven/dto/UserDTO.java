package com.hexaware.cozyhaven.dto;

public class UserDTO {
	
	    private int userId;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String password;
	    private String contactNumber;
	    private String address;
	    private String role;
	    
	    
	    
	    
		public UserDTO() {
			super();
		}
		public UserDTO( String firstName, String lastName, String email,String password, String contactNumber,
				String address, String role) {
			super();
			
			this.firstName = firstName;
			this.lastName = lastName;
			this.password=password;
			this.email = email;
			this.contactNumber = contactNumber;
			this.address = address;
			this.role = role;
		}
		
		
		
		public UserDTO(Integer userId, String firstName, String lastName, String email,
                String password, String contactNumber, String address, String role) {
              this.userId = userId;
             this.firstName = firstName;
              this.lastName = lastName;
            this.email = email;
              this.password = password;
             this.contactNumber = contactNumber;
               this.address = address;
              this.role = role;
 }
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
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
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	    
	    

}
