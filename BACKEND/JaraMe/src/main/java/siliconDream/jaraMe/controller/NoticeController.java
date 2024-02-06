package siliconDream.jaraMe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.*;
import siliconDream.jaraMe.dto.CalendarDTO;
import siliconDream.jaraMe.dto.DailyMissionRecordDTO;
import siliconDream.jaraMe.dto.NoticeDTO;
import siliconDream.jaraMe.repository.*;
import siliconDream.jaraMe.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final ScheduleService scheduleService;
    private final MissionHistoryService missionHistoryService;
    private final JaraUsRepository jaraUsRepository;
    private UserService userService;
    private JaraUsService jaraUsService;
    private JoinUsersService joinUsersService;
    private MissionPostService missionPostService;
    private PointService pointService;
    private DailyMissionRepository dailyMissionRepository;
    private DailyMissionService dailyMissionService;
    private MissionHistoryRepository missionHistoryRepository;
    private ScheduleRepository scheduleRepository;
    private JoinUsersRepository joinUsersRepository;
    public NoticeController(NoticeService noticeService,
                            ScheduleService scheduleService,
                            MissionHistoryService missionHistoryService,
                            JaraUsRepository jaraUsRepository,
                            JaraUsService jaraUsService,
                            JoinUsersService joinUsersService,
                            MissionPostService missionPostService,
                            PointService pointService,
                            UserService userService,
                            DailyMissionRepository dailyMissionRepository,
                            DailyMissionService dailyMissionService,
                            MissionHistoryRepository missionHistoryRepository,
                            ScheduleRepository scheduleRepository,
                            JoinUsersRepository joinUsersRepository) {
        this.noticeService = noticeService;
        this.scheduleService = scheduleService;
        this.missionHistoryService = missionHistoryService;
        this.jaraUsRepository = jaraUsRepository;
        this.jaraUsService = jaraUsService;
        this.joinUsersService = joinUsersService;
        this.missionPostService = missionPostService;
        this.pointService = pointService;
        this.userService = userService;
        this.dailyMissionRepository = dailyMissionRepository;
        this.dailyMissionService = dailyMissionService;
        this.missionHistoryRepository = missionHistoryRepository;
        this.scheduleRepository = scheduleRepository;
        this.joinUsersRepository = joinUsersRepository;
    }

    //미션완주,리액션통계
    @GetMapping("/get")
    public Optional<NoticeDTO> missionFinishNotice(@RequestParam Long userId) {

        /*
        HttpSession session = request.getSession(false);

        Long userId;
        if (session == null){//todo: 로직 추가하기
        }
        User user = (User) session.getAttribute("user");
        // log.info("log:userId:{}", user.getUserId());
        userId = user.getUserId();
        */
        Optional<NoticeDTO> noticeDTO = noticeService.findNoticeMessageByUserIdAndNoticeStatus(userId);
        return noticeDTO;
    }

    //자라어스 생성 시 스케줄링 테스트 용도
    @PostMapping("/scheduling")
    public void scheduling(@RequestParam Long jaraUsId) {
        scheduleService.jaraUsScheduling(jaraUsRepository.findByJaraUsId(jaraUsId));
    }



    //캘린더
    @GetMapping("calendar")
    public Optional<CalendarDTO> calendar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate, @RequestParam Long userId) {

        /*
        HttpSession session = request.getSession(false);

        Long userId;
        if (session == null){//todo: 로직 추가하기
        }
        User user = (User) session.getAttribute("user");
        // log.info("log:userId:{}", user.getUserId());
        userId = user.getUserId();
        */
        Optional<CalendarDTO> calendarDTO = noticeService.getCalendar(selectedDate, userId);
        return calendarDTO;
    }

    //미션완주 테스트 용도
    @PostMapping("/test")
    public void missionComplete() {
        //어제 미션이 종료된 그룹들을 리스트로 얻은 다음,
        List<JaraUs> jaraUses = jaraUsService.findEndDateYesterDay();

        for (JaraUs jaraUs : jaraUses) {
            Long jaraUsId = jaraUs.getJaraUsId();
            log.info("jaraUsId:{}",jaraUsId);

            //jaraUsId로 JoinUsers테이블에 필터링해서 참여중인 유저 알아내기
            Optional<List<Long>> userIds = joinUsersService.findUserIdsByJaraUsId(jaraUsId);

            for (Long userId : userIds.get()) {
                log.info("joined-userId:{}",userId);

                //미션에 참여한 유저들의 참여율 알아내기
                int plusPoint = missionPostService.missionParticipationRate(userId, jaraUsId); //JaraUsService가 더 적합할 것 같음.
                log.info("plusPoint:{}",plusPoint);
                // 1/3 미만 : 미적립
                // 1/3 이상~ 2/3 미만 : 10
                // 2/3 이상~ 전체 미만 : 20
                // 전체 : 50

                if (plusPoint!=0) {
                    //포인트 지급 로직 이용
                    int updatedPoint = pointService.pointPlus(userId, plusPoint, jaraUsId);
                }
            }
        }
    }

    @PostMapping("/test2")
    public void transferDailyMission() {
        log.info("transferDailyMission");
        //모든 유저
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            //데일리미션테이블에 레코드가 있는 경우 => 미션기록테이블에 복사 후 전체 삭제
            List<DailyMission> doneDailyMission = dailyMissionRepository.findByUser_UserId(user.getUserId());
            log.info("doneDateMission:{}", doneDailyMission);
            //데일리미션테이블에 레코드가 있는 경우
            if (doneDailyMission.size() != 0) {
                log.info("doneDailyMission.size:{}", doneDailyMission.size());
                for (DailyMission one : doneDailyMission) {
                    log.info("one");
                    //미션기록테이블에 저장
                    DailyMissionRecordDTO dailyMissionRecordDTO = new DailyMissionRecordDTO();

                    log.info("one.isDailyMissionResult:{}", one.isDailyMissionResult());

                    //미션인증을 하지않은 경우
                    if (!one.isDailyMissionResult()) {
                        dailyMissionRecordDTO.setMissionDate(one.getScheduleDate());
                        dailyMissionRecordDTO.setJaraUs(one.getJaraUs());
                        dailyMissionRecordDTO.setUser(user);
                        dailyMissionRecordDTO.setMissionResult(one.isDailyMissionResult());
                    }
                    else if (one.isDailyMissionResult()) { //미션인증을 한 경우
                        dailyMissionRecordDTO.setMissionDate(one.getScheduleDate());
                        dailyMissionRecordDTO.setJaraUs(one.getJaraUs());
                        dailyMissionRecordDTO.setUser(user);
                        dailyMissionRecordDTO.setMissionResult(one.isDailyMissionResult());
                        dailyMissionRecordDTO.setMissionPost(one.getMissionPost());
                    }

                    log.info("set");
                    missionHistoryRepository.saveDailyMissionRecord(dailyMissionRecordDTO);
                    log.info("save");

                    dailyMissionRepository.deleteByUserUserId(user.getUserId());

                    log.info("delete-done");

                }
            }
            log.info("userId:{}", user.getUserId());
            //해당 유저가 참여하고 있는 자라어스 식별자들을 얻은 후,
            Optional<List<Long>> joinedJaraUsIds = joinUsersRepository.findJaraUs_jaraUsIdsByUser_userId(user.getUserId());
            log.info("joinedJaraUsIds:{}", joinedJaraUsIds);

            for (Long jaraUsId : joinedJaraUsIds.get()) {
                log.info("for-");
                //얻어온 자라어스 식별자들 중 오늘 인증하는 날인 미션이라면 스케줄 레코드를 가져옴
                Optional<Long> todayScheduledJaraUsId = scheduleRepository.findJaraUs_JaraUsIdByScheduleDateAndJaraUsId(LocalDate.now(), jaraUsId);

                if (todayScheduledJaraUsId.isPresent()) {
                    log.info("if-todayScheduledJaraUsId. isPresent? :");
                    //스케줄 레코드와 유저식별자를 통해 오늘의 미션을 업데이트함
                    dailyMissionService.makeDailyMission(user.getUserId(), jaraUsId);
                    log.info("done");
                }
            }

        }
    }

    }
