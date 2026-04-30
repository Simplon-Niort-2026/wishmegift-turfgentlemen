package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public ResponseEntity<User> saveUser( User user) throws RuntimeException {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<User> updateUser(UUID id, User user) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            User currentUser = u.get();

            currentUser.setUsername(user.getUsername());
            currentUser.setEmail((user.getEmail()));
            currentUser.setPassword(user.getPassword());

            saveUser(currentUser);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
