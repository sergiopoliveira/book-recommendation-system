package com.book.service;

import com.book.api.mapper.UserMapper;
import com.book.api.model.UserDTO;
import com.book.domain.User;
import com.book.exceptions.InvalidParameterException;
import com.book.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    private static final Long ID = 1L;
    private static final String NAME = "Joe";
    private static final String EMAIL = "Adams";

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(UserMapper.INSTANCE, userRepository);
    }

    @Test
    public void createNewUserTest() {

        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setName(NAME);
        userDTO.setEmail(EMAIL);

        User savedUser = new User();
        savedUser.setName(userDTO.getName());
        savedUser.setEmail(userDTO.getEmail());
        savedUser.setId(ID);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // when
        UserDTO savedDto = userService.createNewUser(userDTO);

        // then
        assertEquals(userDTO.getName(), savedDto.getName());
        assertEquals(userDTO.getEmail(), savedDto.getEmail());
    }

    @Test(expected = InvalidParameterException.class)
    public void createNewRepeatedUserTest() {

        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setName(NAME);
        userDTO.setEmail(EMAIL);

        User repeatedUser = new User();
        repeatedUser.setName(userDTO.getName());
        repeatedUser.setEmail(userDTO.getEmail());
        repeatedUser.setId(ID);

        // repeated user throws exception
        when(userRepository.findAll()).thenReturn(List.of(repeatedUser));

        // when
        userService.createNewUser(userDTO);

        // then exception is thrown

    }

}