package io.company.service;

import io.company.dto.UserDTO;
import io.company.entity.User;
import io.company.repository.UserRepository;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
class UserServiceTest {
    @Inject
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Test
    void all() {
        //Given
        User user = user();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        //When
        List<User> userList = userService.all();

        //Then
        assertEquals(Arrays.asList(user), userList);
        verify(userRepository).findAll();
    }

    @Test
    void get() {
        //Given
        User user = user();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        //When
        User actual = userService.get(user.getId());

        //Then
        assertEquals(user, actual);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void add() {
        //Given
        UserDTO userDTO = userDTO();
        User user = user();
        when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        User actual = userService.add(userDTO);

        //Then
        assertEquals(actual.getName(), userDTO.getName());
        assertEquals(actual.getHashedPassword(), userDTO.getHashedPassword());
        verify(userRepository).save(any(User.class));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void update() {
        //Given
        UserDTO userDTO = userDTO();
        User user = user();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.update(any(User.class))).thenReturn(user);

        //When
        User actual = userService.update(user.getId(), userDTO);

        //Then
        assertEquals(user, actual);
        verify(userRepository).findById(user.getId());
        verify(userRepository).update(any(User.class));
    }

    @Test
    void delete() {
        //Given
        doNothing().when(userRepository).deleteById(any(Long.class));

        //When
        userService.delete(10L);

        //Then
        verify(userRepository).deleteById(any(Long.class));
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

    @MockBean(UserRepository.class)
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}