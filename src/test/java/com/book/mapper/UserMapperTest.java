package com.book.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.book.api.mapper.UserMapper;
import com.book.api.model.UserDTO;
import com.book.domain.User;

@Ignore
public class UserMapperTest {

	private static final String NAME = "Freddy";
	private static final String EMAIL = "freddy@gmail.com";
	
	UserMapper userMapper = UserMapper.INSTANCE;
	
	@Test
	public void userToUserDTOTest() throws Exception {
		
		// given
		User user = new User();
		user.setName(NAME);
		user.setEmail(EMAIL);
		
		// when 
		UserDTO userDTO = userMapper.userToUserDTO(user);
		
		// then
		assertEquals(NAME, userDTO.getName());
		assertEquals(EMAIL, userDTO.getEmail());
}
}
