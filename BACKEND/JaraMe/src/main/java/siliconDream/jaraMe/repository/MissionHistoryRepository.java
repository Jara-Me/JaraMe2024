package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import siliconDream.jaraMe.domain.MissionHistory;
import siliconDream.jaraMe.dto.DailyMissionRecordDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MissionHistoryRepository extends JpaRepository<MissionHistory,Long> {

    @Query("SELECT m.missionDate " +
            "FROM MissionHistory m " +
            "LEFT JOIN m.jaraUs mj " +
            "LEFT JOIN m.user mu " +
            "WHERE mu.userId = :userId AND mj.jaraUsId = :jaraUsId AND m.missionResult = :missionResult")
    Set<LocalDate> findMissionDateByUser_UserIdAndJaraUs_JaraUsIdAndMissionResult(Long userId, Long jaraUsId,boolean missionResult);

    default void saveDailyMissionRecord(DailyMissionRecordDTO dailyMissionRecordDTO){
        MissionHistory missionHistory = new MissionHistory();
        missionHistory.setMissionDate(dailyMissionRecordDTO.getMissionDate());
        missionHistory.setJaraUs(dailyMissionRecordDTO.getJaraUs());
        missionHistory.setUser(dailyMissionRecordDTO.getUser());
        missionHistory.setMissionPost(dailyMissionRecordDTO.getMissionPost());
        missionHistory.setMissionResult(dailyMissionRecordDTO.isMissionResult());
        save(missionHistory);
    }

    List<Long> findMissionPostIdsByUser_UserId(Long userId);


    @Query("SELECT mh.missionDate, COUNT(*) as count " +
            "FROM MissionHistory mh " +
            "LEFT JOIN mh.user mhu " +
            "WHERE mhu.userId = :userId AND mh.missionResult = :missionResult " +
            "GROUP BY mh.missionDate")
    Optional<List<Object[]>> findMissionDateByUser_UserIdAndMissionResult(Long userId, boolean missionResult);
}
