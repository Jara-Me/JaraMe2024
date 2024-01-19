package siliconDream.jaraMe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {


    @Modifying
    @Query ("INSERT INTO Schedule (jaraUsId,scheduleDate) VALUES (:jaraUsId, :targetDate)")
    default void saveSchedule(Long jaraUsId, LocalDate targetDate){

     }

}
