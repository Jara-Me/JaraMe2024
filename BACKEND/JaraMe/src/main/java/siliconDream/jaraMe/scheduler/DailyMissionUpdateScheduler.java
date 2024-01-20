package siliconDream.jaraMe.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.domain.Schedule;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.DailyMissionRecordDTO;
import siliconDream.jaraMe.repository.DailyMissionRepository;
import siliconDream.jaraMe.repository.JoinUsersRepository;
import siliconDream.jaraMe.repository.MissionHistoryRepository;
import siliconDream.jaraMe.repository.ScheduleRepository;
import siliconDream.jaraMe.service.DailyMissionService;
import siliconDream.jaraMe.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyMissionUpdateScheduler {

    private final DailyMissionRepository dailyMissionRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final JoinUsersRepository joinUsersRepository;
    private final DailyMissionService dailyMissionService;

    public DailyMissionUpdateScheduler(DailyMissionRepository dailyMissionRepository,
                                       MissionHistoryRepository missionHistoryRepository,
                                       ScheduleRepository scheduleRepository,
                                       UserService userService,
                                       JoinUsersRepository joinUsersRepository,
                                       DailyMissionService dailyMissionService) {
        this.dailyMissionRepository = dailyMissionRepository;
        this.missionHistoryRepository = missionHistoryRepository;
        this.scheduleRepository = scheduleRepository;
        this.userService = userService;
        this.joinUsersRepository = joinUsersRepository;
        this.dailyMissionService = dailyMissionService;}

    @Scheduled(cron = "0 0 0 * * *")
    public void transferDailyMission() {

        //모든 유저
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            //데일리미션테이블에 레코드가 있는 경우 => 미션기록테이블에 복사 후 전체 삭제
            List<DailyMission> doneDailyMission = dailyMissionRepository.findAll();
            if (!doneDailyMission.isEmpty()) {
                for (DailyMission one : doneDailyMission) {
                    if (one.isDailyMissionResult()) {
                        DailyMissionRecordDTO dailyMissionRecordDTO = new DailyMissionRecordDTO();
                        dailyMissionRecordDTO.setMissionDate(one.getDoneDateTime().toLocalDate());
                        missionHistoryRepository.saveDailyMissionRecord(dailyMissionRecordDTO);

                        dailyMissionRepository.deleteAll();
                    }

                }
            }
            //해당 유저가 참여하고 있는 자라어스 식별자들을 얻은 후,
            List<Long> joinedJaraUsIds = joinUsersRepository.findJaraUs_jaraUsIdsByUser_userId(user.getUserId());

            //얻어온 자라어스 식별자들 중 오늘 인증하는 날인 미션이라면 스케줄 레코드를 가져옴
            List<Schedule> todaySchedule = scheduleRepository.findScheduleDateByTodayAndJaraUsId(LocalDate.now(),joinedJaraUsIds);

            //스케줄 레코드와 유저식별자를 통해 오늘의 미션을 업데이트함
            dailyMissionService.makeDailyMission(user.getUserId(),todaySchedule);



        }
    }
}
