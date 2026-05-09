package co.simplon.wishmegift.dto;

public class UserResponseDTO {

    private String username;
    private String email;

    public UserResponseDTO() {}

    public UserResponseDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
