package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siliconDream.jaraMe.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    String findEmailByEmail(String email);

    String findNicknameByNickname(String nickname);

    //수정한 부분
    User findByUserId(Long userId);


}