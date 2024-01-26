package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import siliconDream.jaraMe.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    String findEmailByEmail(String email);

    String findNicknameByNickname(String nickname);

    //수정한 부분
    User findByUserId(Long userId);

    //이메일로 사용자 찾기
    User findByEmail(String email);

    @Query ("SELECT u.passTicket " +
            "FROM User u " +
            "WHERE u.userId = :userId")
    int findPassTicketByUserId(Long userId);

}
