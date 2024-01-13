package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.UserDto;

public interface UserService {

    boolean create(UserDto userDto);

    String emailCheck(String email);

    boolean isPasswordConfirmed(UserDto userDto);
}