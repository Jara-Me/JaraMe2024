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
    /*오류나는 부분-커밋
    User findUserByUsername(String username); */
}