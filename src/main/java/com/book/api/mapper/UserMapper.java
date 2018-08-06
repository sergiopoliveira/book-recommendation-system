package com.book.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.book.api.model.UserDTO;
import com.book.domain.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO userToUserDTO(User user);
	
	User userDTOToUser(UserDTO userDTO);
}
