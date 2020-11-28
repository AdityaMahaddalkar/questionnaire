package io.company.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "USER_IDENTIFICATION")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "USER_HASHED_PASSWORD", nullable = false, unique = true)
    private String hashedPassword;

    @NotNull
    @DateCreated
    @Column(name = "CREATED_TIMESTAMP", nullable = false)
    private LocalDateTime created;

    @NotNull
    @DateUpdated
    @Column(name = "UPDATED_TIMESTAMP", nullable = false)
    private LocalDateTime updated;

    public User() {
    }

    public User(@NotNull String name, @NotNull String hashedPassword) {
        this.name = name;
        this.hashedPassword = hashedPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public @NotNull LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(@NotNull LocalDateTime created) {
        this.created = created;
    }

    public @NotNull LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(@NotNull LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                hashedPassword.equals(user.hashedPassword) &&
                created.equals(user.created) &&
                updated.equals(user.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hashedPassword, created, updated);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
