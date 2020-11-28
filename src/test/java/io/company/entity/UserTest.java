package io.company.entity;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UserTest {

    private final User user;
    private final LocalDateTime localDate;

    UserTest() {
        localDate = LocalDateTime.now();
        user = new User();
        user.setId(10L);
        user.setName("U1");
        user.setHashedPassword("P1");
        user.setCreated(localDate);
        user.setUpdated(localDate);

    }


    @Test
    void getId() {
        Long expected = 10L;
        assertEquals(expected, user.getId());
    }

    @Test
    void setId() {
        user.setId(20L);
        Long expected = 20L;
        assertEquals(expected, user.getId());
    }

    @Test
    void getName() {
        String expected = "U1";
        assertEquals(expected, user.getName());
    }

    @Test
    void setName() {
        user.setName("U2");
        String expected = "U2";
        assertEquals(expected, user.getName());
    }

    @Test
    void getHashedPassword() {
        String expected = "P1";
        assertEquals(expected, user.getHashedPassword());
    }

    @Test
    void setHashedPassword() {
        user.setHashedPassword("P2");
        String expected = "P2";
        assertEquals(expected, user.getHashedPassword());
    }

    @Test
    void getCreated() {
        assertEquals(localDate, user.getCreated());
    }

    @Test
    void setCreated() {
        user.setCreated(localDate);
        assertEquals(localDate, user.getCreated());
    }

    @Test
    void getUpdated() {
        assertEquals(localDate, user.getUpdated());
    }

    @Test
    void setUpdated() {
        user.setUpdated(localDate);
        assertEquals(localDate, user.getUpdated());
    }

    @Test
    void testEquals() {
        User user1 = user;
        User user2 = new User();
        assertTrue(user.equals(user1));
        assertFalse(user.equals(user2));
    }

    @Test
    void testHashCode() {
        User user1 = user;
        assertEquals(user.hashCode(), user1.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(user.toString());
    }
}