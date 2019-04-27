package com.book.service;

import com.book.api.mapper.UserMapper;
import com.book.api.model.UserDTO;
import com.book.domain.User;
import com.book.exceptions.InvalidParameterException;
import com.book.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {

        // check if user with that name already exists
        if (userRepository.findAll()
                .stream()
                .anyMatch(user -> user.getName().equals(userDTO.getName()))) {
            throw new InvalidParameterException("Username already exists");
        }

        // save and return user DTO
        User user = userMapper.userDTOToUser(userDTO);
        return saveAndReturnDTO(user);
    }

    private UserDTO saveAndReturnDTO(User user) {

        User savedUser = userRepository.save(user);

        return userMapper.userToUserDTO(savedUser);
    }

}
