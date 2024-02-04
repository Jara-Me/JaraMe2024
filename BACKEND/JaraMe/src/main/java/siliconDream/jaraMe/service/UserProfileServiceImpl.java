package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.UserProfileInfoDTO;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserProfileInfoDTO getUserProfileInfo(Long userId) {
        User user = userService.findUserByUserId(userId); // User 정보 조회

        UserProfileInfoDTO userProfileInfoDTO = new UserProfileInfoDTO();
        userProfileInfoDTO.setUserId(user.getUserId());
        userProfileInfoDTO.setNickname(user.getNickname());
        userProfileInfoDTO.setProfileImage(user.getProfileImage());
        userProfileInfoDTO.setPoints(userService.getPoints(userId));
        userProfileInfoDTO.setPassTicket(userService.getPassTicket(userId));
        userProfileInfoDTO.setParticipatingJaraUsCount(userService.getParticipatingJaraUsCount(userId));

        return userProfileInfoDTO;
    }
}
