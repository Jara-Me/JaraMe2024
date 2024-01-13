package siliconDream.jaraMe.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String nickname;
    private String password;
    private String confirmPassword;
    private String email;
    private String dateOfBirth; // You can use a more appropriate type for date of birth
    private List<String> interests;

    // Constructors, getters, and setters

    public UserDto() {
    }

    public UserDto(Long id, String nickname, String password, String confirmPassword, String email, String dateOfBirth, List<String> interests) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.interests = interests;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    // You can add more getters and setters for additional fields

    // toString method for debugging and logging

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", interests=" + interests +
                '}';
    }
}