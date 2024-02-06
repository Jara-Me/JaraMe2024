package siliconDream.jaraMe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.JoinUsers;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.CalendarDTO;
import siliconDream.jaraMe.dto.NoticeDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@CrossOrigin(originPatterns = "http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final ScheduleService scheduleService;
    private final MissionHistoryService missionHistoryService;
    private final JaraUsRepository jaraUsRepository;

    private JaraUsService jaraUsService;
    private JoinUsersService joinUsersService;
    private MissionPostService missionPostService;
    private PointService pointService;

    public NoticeController(NoticeService noticeService,
                            ScheduleService scheduleService,
                            MissionHistoryService missionHistoryService,
                            JaraUsRepository jaraUsRepository,
                            JaraUsService jaraUsService,
                            JoinUsersService joinUsersService,
                            MissionPostService missionPostService,
                            PointService pointService) {
        this.noticeService = noticeService;
        this.scheduleService = scheduleService;
        this.missionHistoryService = missionHistoryService;
        this.jaraUsRepository = jaraUsRepository;
        this.jaraUsService = jaraUsService;
        this.joinUsersService = joinUsersService;
        this.missionPostService = missionPostService;
        this.pointService = pointService;
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
}
