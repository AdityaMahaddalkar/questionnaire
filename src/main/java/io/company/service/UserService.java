package io.company.service;

import io.company.dto.UserDTO;
import io.company.entity.User;
import io.company.exception.ResourceNotFoundException;
import io.company.repository.UserRepository;
import io.micronaut.core.annotation.Introspected;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Introspected
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> all() {
        return (List<User>) userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> resourceNotFoundException(id));
    }

    public User add(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setHashedPassword(userDTO.getHashedPassword());
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> resourceNotFoundException(id));
        user.setName(userDTO.getName());
        user.setHashedPassword(userDTO.getHashedPassword());
        user.setUpdated(LocalDateTime.now());
        return userRepository.update(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private ResourceNotFoundException resourceNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("User with id = %d not found", id));
    }


}
