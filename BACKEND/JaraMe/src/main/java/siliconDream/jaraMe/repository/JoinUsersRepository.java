package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.JoinUsers;
import siliconDream.jaraMe.domain.User;

import java.util.List;

@Repository
public interface JoinUsersRepository extends JpaRepository<JoinUsers,Long> {

    List<Long> findUserIdsByJaraUs_JaraUsId(Long jaraUsId);

}
