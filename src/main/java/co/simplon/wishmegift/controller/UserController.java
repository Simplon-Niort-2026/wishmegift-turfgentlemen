package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserResponseDTO;
import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.mapper.UserMapper;
import co.simplon.wishmegift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        Optional<UserResponseDTO> userResponseDTO = userService.getUserById(id);
        return userResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> userRegister(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        Optional<UserResponseDTO> userResponseDTO = userService.createUser(userCreateDTO);
        return userResponseDTO.map(responseDTO -> new ResponseEntity<>(responseDTO, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable UUID id, @RequestBody UserCreateDTO userCreateDTO) {
        Optional<UserResponseDTO> userResponseDTO = userService.updateUser(id, userCreateDTO);
        return userResponseDTO.map(ResponseDTO -> new ResponseEntity<>(ResponseDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
