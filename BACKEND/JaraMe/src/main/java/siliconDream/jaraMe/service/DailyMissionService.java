package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Schedule;
import siliconDream.jaraMe.dto.DailyMissionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyMissionService {

    void makeDailyMission(Long userId, List<Schedule> todaySchedule);
    List<DailyMissionDTO> getDailyMission(Long userId, LocalDate todayDate) ;
}
