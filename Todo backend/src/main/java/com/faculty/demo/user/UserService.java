package com.faculty.demo.user;

import com.faculty.demo.dto.UserLoginDTO;
import com.faculty.demo.dto.UserRequestDTO;
import com.faculty.demo.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean checkIfUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkIfUserExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public void addNewUser(UserRequestDTO userRequestDTO) {
        User user = new User(
                userRequestDTO.firstName(),
                userRequestDTO.lastName(),
                userRequestDTO.email(),
                userRequestDTO.password()
        );
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ApiRequestException("User with id " + id + " does not exist!");
        }
        userRepository.deleteById(id);
    }

    public void updateUserById(Long id, UserRequestDTO userRequestDTO) {
        if (!userRepository.existsById(id)) {
            throw new ApiRequestException("User with id " + id + " does not exist!");
        }

        User user = new User(
                userRequestDTO.firstName(),
                userRequestDTO.lastName(),
                userRequestDTO.email(),
                userRequestDTO.password()
        );
        userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ApiRequestException("User with id " + id + " does not exist!");
        }
        return userRepository.findById(id).get();
    }

    public Long loginUser(UserLoginDTO userLogin) {
        try {
            User user = userRepository.findByEmailAndPassword(userLogin.email(), userLogin.password());
            return user.getId();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
