package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserResponseDTO;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
    }

    public Optional<UserResponseDTO> getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toUserResponseDTO);
    }

    public Optional<UserResponseDTO> createUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            return Optional.empty();
        }

        User user = userMapper.toUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return Optional.of(userMapper.toUserResponseDTO(user));

    }

    public Optional<UserResponseDTO> updateUser(UUID id, UserCreateDTO userCreateDTO) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            User currentUser = u.get();
            currentUser.setUsername(userCreateDTO.getUsername());
            currentUser.setEmail((userCreateDTO.getEmail()));
            currentUser.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

            userRepository.save(currentUser);
            return Optional.of(userMapper.toUserResponseDTO(currentUser));

        }
        return Optional.empty();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
