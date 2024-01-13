package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dao.UserDao;
import siliconDream.jaraMe.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public boolean create(UserDto userDto) {
        // 비밀번호 확인 로직 추가
        if (!isPasswordConfirmed(userDto)) {
            return false; // 비밀번호 확인이 일치하지 않으면 가입 실패
        }

        // 중복 체크
        String checkTF = userDao.signUpCheck(userDto);
        if (checkTF == null) {
            userDao.insert(userDto);
            return true; // 중복값이 없으면 가입 성공
        } else {
            return false; // 중복값이 있으면 가입 실패
        }
    }

    @Override
    public String emailCheck(String email) {
        return userDao.emailCheck(email);
    }

    @Override
    public boolean isPasswordConfirmed(UserDto userDto) {
        // 비밀번호 확인 로직을 수행하여 결과 반환
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}