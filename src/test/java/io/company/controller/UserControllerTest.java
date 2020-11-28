package io.company.controller;

import io.company.dto.UserDTO;
import io.company.entity.User;
import io.company.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
class UserControllerTest {
    @Inject
    UserService userService;

    @Inject
    UserController userController;

    @Test
    void all() {
        //Given
        User user = user();
        when(userService.all()).thenReturn(Arrays.asList(user));

        //When
        List<User> userList = userController.all();

        //Then
        assertEquals(Arrays.asList(user), userList);
        verify(userService).all();
    }

    @Test
    void get() {
        //Given
        User user = user();
        when(userService.get(user.getId())).thenReturn(user);

        //When
        HttpResponse<User> actual = userController.get(user.getId());

        //Then
        assertEquals(HttpStatus.OK, actual.status());
        verify(userService).get(user.getId());
    }

    @Test
    void add() {
        //Given
        UserDTO userDTO = userDTO();
        User user = user();
        when(userService.add(userDTO)).thenReturn(user);

        //When
        HttpResponse<User> actual = userController.add(userDTO);

        //Then
        assertEquals(HttpStatus.OK, actual.status());
        assertEquals(user, actual.body());
        verify(userService).add(userDTO);
    }

    @Test
    void update() {
        //Given
        UserDTO userDTO = userDTO();
        User user = user();
        when(userService.update(user.getId(), userDTO)).thenReturn(user);

        //When
        HttpResponse<User> actual = userController.update(user.getId(), userDTO);

        //Then
        assertEquals(HttpStatus.OK, actual.status());
        assertEquals(user, actual.body());
        verify(userService).update(user.getId(), userDTO);
    }

    @Test
    void delete() {
        //Given
        User user = user();
        doNothing().when(userService).delete(user.getId());

        //When
        userController.delete(user.getId());

        //Then
        verify(userService).delete(user.getId());
    }

    @MockBean(UserService.class)
    UserService userService() {
        return mock(UserService.class);
    }

    private User user() {
        LocalDateTime localDate = LocalDateTime.now();
        User user;
        user = new User();
        user.setId(10L);
        user.setName("U1");
        user.setHashedPassword("P1");
        user.setCreated(localDate);
        user.setUpdated(localDate);
        return user;
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("U1");
        userDTO.setHashedPassword("P1");
        return userDTO;
    }
}