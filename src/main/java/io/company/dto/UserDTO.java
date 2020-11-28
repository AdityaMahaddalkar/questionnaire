package io.company.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class UserDTO {
    private String name;
    private String hashedPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
