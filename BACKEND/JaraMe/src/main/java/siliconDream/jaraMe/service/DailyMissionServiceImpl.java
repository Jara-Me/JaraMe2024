package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.domain.Schedule;
import siliconDream.jaraMe.repository.DailyMissionRepository;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.repository.UserRepository;

import java.util.List;

@Service
public class DailyMissionServiceImpl implements DailyMissionService{

    private final UserRepository userRepository;
    private final JaraUsRepository jaraUsRepository;
    private final DailyMissionRepository dailyMissionRepository;

    public DailyMissionServiceImpl(UserRepository userRepository,
                                   JaraUsRepository jaraUsRepository,
                                   DailyMissionRepository dailyMissionRepository) {
        this.userRepository = userRepository;
        this.jaraUsRepository = jaraUsRepository;
        this.dailyMissionRepository = dailyMissionRepository;
    }

    public void makeDailyMission(Long userId, List<Schedule> todaySchedule){
        for (Schedule one : todaySchedule){
            DailyMission dailyMission = new DailyMission();
            //dailyMission.setDailyMissionDate(); => 미션 하면 날짜 기록

            dailyMission.setUser(userRepository.findByUserId(userId));
            dailyMission.setJaraUs(jaraUsRepository.findByJaraUsId(one.getJaraUsId()));
            dailyMissionRepository.save(dailyMission);
        }
    }
}
