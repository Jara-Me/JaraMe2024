package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Schedule;

import java.util.List;

public interface DailyMissionService {

    void makeDailyMission(Long userId, List<Schedule> todaySchedule);
}
