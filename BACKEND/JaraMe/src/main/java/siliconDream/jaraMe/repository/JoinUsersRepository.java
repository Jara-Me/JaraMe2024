package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.JoinUsers;
import siliconDream.jaraMe.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinUsersRepository extends JpaRepository<JoinUsers,Long> {

    List<Long> findUserIdsByJaraUs_JaraUsId(Long jaraUsId);

    @Query("SELECT jj.jaraUsId " +
            "FROM JoinUsers j " +
            "LEFT JOIN j.jaraUs as jj " +
            "LEFT JOIN j.user ju " +
            "WHERE ju.userId = :userId")
    Optional<List<Long>> findJaraUs_jaraUsIdsByUser_userId(Long userId);
}
