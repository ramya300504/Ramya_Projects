package com.hexaware.cozyhaven.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hexaware.cozyhaven.entity.User;

public class UserConverter {
	
	public static UserDTO toDTO(User user)
	{
       

        UserDTO dto = new UserDTO();

        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setContactNumber(user.getContactNumber());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole().name());

        return dto;
    }

    public static List<UserDTO> toDTOList(List<User> userList) {
        return userList.stream()
                       .map(UserConverter::toDTO)
                       .collect(Collectors.toList());
    }

}
