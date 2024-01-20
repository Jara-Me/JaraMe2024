package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.dto.DailyMissionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyMissionRepository extends JpaRepository<DailyMission, Long> {

    //userId로 오늘의 미션 조회
    List<DailyMission> findByUser_UserId(Long userId);

    //오늘의 미션(개별 미션) 진행상황 업데이트
    @Modifying
    @Query(
            "UPDATE DailyMission dm SET dm.dailyMissionResult = true " +
                    "WHERE dm.user.userId = :userId AND dm.jaraUs.jaraUsId= :jaraUsId")
    default void updateDailyMissionStatus(Long userId, Long jaraUsId) {
    }

    void deleteAll();


    @Query("SELECT new siliconDream.jaraMe.dto.DailyMissionDTO(" +
            "d.dailyMissionResult, " +
            "dj.jaraUsName, dj.missionName) "+
            "FROM DailyMission d "+
            "LEFT JOIN d.jaraUs dj "+
            "WHERE d.user.userId = :userId AND d.scheduleDate = :todayDate")
    List<DailyMissionDTO> findByUser_UserIdAndScheduleDate(Long userId, LocalDate todayDate);



    //dailyMission테이블에 있는지 찾아보기 => dailyMissionResult==true면 가장 최근 게시글이 MissionPostId와 동일하지도 체크 ? 아니면 DailyMission,MissionHistory테이블에 미션포스트넘버 추가?
    //Optional<Long> postedMissionPostId = dailyMissionRepository.findMissionPostIdByUser_UserIdAndScheduleDateAndDailyMissionResult(userId,todayDate,true);
    @Query("SELECT dm.missionPostId "+
    "FROM DailyMission dm "+
    "WHERE dm.user.userId= :userId AND dm.scheduleDate = :todayDate AND dm.dailyMissionResult = :dailyMissionResult")
    Optional<Long> findMissionPostIdByUser_UserIdAndScheduleDateAndDailyMissionResult(Long userId, LocalDate todayDate, boolean dailyMissionResult);


    List<Long> findMissionPostIdsByUser_UserId(Long userId); //TODO: missionPostId 컬럼 추가하기
}
