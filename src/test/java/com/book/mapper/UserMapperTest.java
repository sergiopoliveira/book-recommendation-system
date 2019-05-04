package com.book.mapper;

import com.book.api.mapper.UserMapper;
import com.book.api.model.UserDTO;
import com.book.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {

    private static final String NAME = "Freddy";
    private static final String EMAIL = "freddy@gmail.com";

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void userToUserDTOTest() {

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

    @Test
    public void userDTOToUserTest() {

        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setName(NAME);
        userDTO.setEmail(EMAIL);

        // when
        User user = userMapper.userDTOToUser(userDTO);

        // then
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }
}
