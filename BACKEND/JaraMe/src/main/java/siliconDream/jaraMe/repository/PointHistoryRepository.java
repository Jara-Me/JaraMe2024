package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.PointHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    //해당 유저가 미션완주로 포인트 지급받았는제 공지사항전달한 적이 없는 기록 가져오기
    @Query("SELECT ph " +
            "FROM PointHistory ph " +
            "LEFT JOIN ph.user as phu " +
            "WHERE phu.userId = :userId AND ph.notice = :notice")
    Optional<List<PointHistory>> findByUser_UserIdAndNotice(Long userId, boolean notice);


}
