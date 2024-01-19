package siliconDream.jaraMe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {


    @Modifying
    @Query ("INSERT INTO Schedule (jaraUsId,scheduleDate) VALUES (:jaraUsId, :targetDate)")
    default void saveSchedule(Long jaraUsId, LocalDate targetDate){

     }

    Set<LocalDate> findScheduleDateByJaraUsId(Long jaraUsId);

    //// 자라어스 식별자들 중 오늘 인증하는 날인 미션이라면 스케줄 레코드를 전달함
    @Query("SELECT s FROM Schedule s WHERE s.scheduleDate = :today AND s.jaraUsId IN :jaraUsIds")
    List<Schedule> findScheduleDateByTodayAndJaraUsId(@Param("today") LocalDate today,@Param("jaraUsIds") List<Long> jaraUsIds);


}
