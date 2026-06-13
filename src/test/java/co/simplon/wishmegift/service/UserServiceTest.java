package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserResponseDTO;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;


    @Test
    void shouldGetAllUsers() {
        // Arrange
        UserCreateDTO michel = new UserCreateDTO(1L, "michel", "michel@mail.com", "michel");
        UserCreateDTO bobby = new UserCreateDTO(2L, "bobby", "bobby@mail.com", "bobby");
        UserCreateDTO claudine = new UserCreateDTO(3L, "claudine", "claudine@mail.com", "claudine");


        // Act
        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();


        // Assert
        assertEquals(3, users.size());

    }

    @Test
    void shouldFoundOptionalIsEmptyWhenEmailAlreadyExists() {
        UserCreateDTO userCreateDTO = new UserCreateDTO(1L, "userTest", "test@mail.com", "test");


        when(userRepository.existsByEmail(userCreateDTO.getEmail())).thenReturn(true);

        assertEquals(Optional.empty(),userService.createUser(userCreateDTO));

    }

    @Test
    void shouldCreateUser() {

        UserCreateDTO userCreateDTO = new UserCreateDTO(1L, "userTest", "test@mail.com", "test");
        User user = new User();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userRepository.existsByEmail(userCreateDTO.getEmail())).thenReturn(false);
        when(userMapper.toUser(userCreateDTO)).thenReturn(user);
        when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        assertEquals(Optional.of(userResponseDTO), userService.createUser(userCreateDTO));
        verify(passwordEncoder).encode(user.getPassword());
    }

    @Test
    void shouldFoundOptionalIsEmptyWhenUserIsNotPresent() {
        UserCreateDTO userCreateDTO = new UserCreateDTO(1L, "userTest", "test@mail.com", "test");

        when(userRepository.findById(userCreateDTO.getId())).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), userService.updateUser(userCreateDTO.getId(), userCreateDTO));
    }

    @Test
    void shouldUpdateUser() {
        // Arrange
        Long id = 1L;
        UserCreateDTO userCreateDTO = new UserCreateDTO(id, "userTest", "test@mail.com", "test");
        User user = new User();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        // Act & Assert
        assertEquals(Optional.of(userResponseDTO), userService.updateUser(id, userCreateDTO));
        verify(passwordEncoder).encode(userCreateDTO.getPassword());
    }



}