package io.company.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UserDTOTest {

    @Test
    void getName() {
        String expected = "U1";
        assertEquals(expected, userDTO().getName());
    }

    @Test
    void setName() {
        UserDTO userDTO = userDTO();
        userDTO.setName("U2");
        String expected = "U2";
        assertEquals(expected, userDTO.getName());
    }

    @Test
    void setHashedPassword() {
        String expected = "P1";
        assertEquals(expected, userDTO().getHashedPassword());
    }

    @Test
    void getHashedPassword() {
        UserDTO userDTO = userDTO();
        userDTO.setHashedPassword("P2");
        String expected = "P2";
        assertEquals(expected, userDTO.getHashedPassword());
    }

    UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("U1");
        userDTO.setHashedPassword("P1");
        return userDTO;
    }
}