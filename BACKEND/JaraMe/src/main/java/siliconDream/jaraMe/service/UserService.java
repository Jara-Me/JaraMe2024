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

    //수정한 부분
    User findUserByUserId(Long userId);

    //로그인 메소드
    LoginResponse login(String email, String password);

    User findUserByEmail(String email);

    // 회원 탈퇴
    void deleteUser(Long userId);

    // 프로필 사진 업로드 및 수정
    String updateProfileImage(Long userId, MultipartFile profileImagePath) throws IOException;

    //닉네임 변경 메소드
    boolean changeNickname(Long userId, String newNickname, String password);


    int getPassTicket(Long userId);
}
