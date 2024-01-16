package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.UserDto;

import java.util.List;

public interface UserService {

    boolean create(UserDto userDto);

    String emailCheck(String email);

    boolean isPasswordConfirmed(UserDto userDto);

    List<User> getAllUsers();

    void saveUser(User user);
    User findUserByUsername(String username);
}