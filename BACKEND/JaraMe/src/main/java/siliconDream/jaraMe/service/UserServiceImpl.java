package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.UserDto;
import siliconDream.jaraMe.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean create(UserDto userDto) {
        if (!isPasswordConfirmed(userDto)) {
            return false; // Password confirmation failed
        }

        // Check for email duplication using JpaRepository
        if (userRepository.findEmailByEmail(userDto.getEmail()) != null) {
            return false; // Duplicate email
        }

        // Check for nickname duplication using JpaRepository
        if (userRepository.findNicknameByNickname(userDto.getNickname()) != null) {
            return false; // Duplicate nickname
        }

        // Create and save the user entity
        User user = new User();
        user.setProfileImage(userDto.getProfileImage());
        user.setNickname(userDto.getNickname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        String birthDateString = userDto.getBirthDate();
        LocalDate birthDate = LocalDate.parse(birthDateString);
        user.setBirthDate(birthDate);

        // Set other fields as needed
        user.setCheckIn(false); // Assuming a new user is not checked in by default
        user.setPoint(0);
        user.setPassTicket(0);

        userRepository.save(user);
        return true; // Successful registration
    }

    @Override
    public String emailCheck(String email) {
        return userRepository.findEmailByEmail(email);
    }

    @Override
    public boolean isPasswordConfirmed(UserDto userDto) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByUserId(Long userId) {
        // Implement the logic to find a user by username
        //수정한 부분
        return userRepository.findByUserId(userId);
    }
}