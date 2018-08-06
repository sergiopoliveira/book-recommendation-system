package com.book.service;

import org.springframework.stereotype.Service;

import com.book.api.mapper.UserMapper;
import com.book.api.model.UserDTO;
import com.book.domain.User;
import com.book.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}


	@Override
	public UserDTO createNewUser(UserDTO userDTO) {

		User user = userMapper.userDTOToUser(userDTO);
		
		return saveAndReturnDTO(user);
	}
	
	private UserDTO saveAndReturnDTO(User user) {
		
		User savedUser = userRepository.save(user);
		
		UserDTO returnDTO = userMapper.userToUserDTO(savedUser);
		
		//returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
		
		return returnDTO;
}

}
