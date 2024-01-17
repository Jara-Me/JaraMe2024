package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.DailyMission;

import java.util.List;

@Repository
public interface DailyMissionRepository extends JpaRepository<DailyMission, Long> {

    //userId로 오늘의 미션 조회
    List<DailyMission> findByUserId(Long userId);

    //오늘의 미션(하나) 진행상황 업데이트
    @Modifying
    @Query(
            "UPDATE DailyMission dm SET dm.dailyMissionResult = true " +
                    "WHERE dm.userId = :userId AND dm.groupId= :groupId")
    default void updateDailyMissionStatus(Long userId, Long groupId) {
    }


}
