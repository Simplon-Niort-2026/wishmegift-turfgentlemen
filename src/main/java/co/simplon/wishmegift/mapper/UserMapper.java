package co.simplon.wishmegift.mapper;

import co.simplon.wishmegift.dto.UserCreateDTO;
import co.simplon.wishmegift.dto.UserResponseDTO;
import co.simplon.wishmegift.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public User toUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }


}
