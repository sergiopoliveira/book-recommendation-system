package com.book.controllers;

import com.book.api.model.UserDTO;
import com.book.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testCreateNewUser() throws Exception {
        // given
        UserDTO user1 = new UserDTO();
        user1.setName("Jack");
        user1.setEmail("jack@gmail.com");

        UserDTO returnDTO = new UserDTO();
        returnDTO.setName(user1.getName());
        returnDTO.setEmail(user1.getEmail());

        when(userService.createNewUser(user1)).thenReturn(returnDTO);

        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(user1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Jack")))
                .andExpect(jsonPath("$.email", equalTo("jack@gmail.com")));

    }

}
