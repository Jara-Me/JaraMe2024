package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.NoticeDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.service.JaraUsService;
import siliconDream.jaraMe.service.MissionHistoryService;
import siliconDream.jaraMe.service.NoticeService;
import siliconDream.jaraMe.service.ScheduleService;

import java.util.Optional;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final ScheduleService scheduleService;
    private final MissionHistoryService missionHistoryService;
    private final JaraUsRepository jaraUsRepository;

    public NoticeController(NoticeService noticeService,
                            ScheduleService scheduleService,
                            MissionHistoryService missionHistoryService,
                            JaraUsRepository jaraUsRepository){
        this.noticeService=noticeService;
        this.scheduleService =scheduleService;
        this.missionHistoryService = missionHistoryService;
        this.jaraUsRepository = jaraUsRepository;
    }

    //미션완주,리액션통계
    @GetMapping("/get")
    public Optional<NoticeDTO> missionFinishNotice(@RequestParam Long userId){
        Optional<NoticeDTO> noticeDTO =noticeService.findNoticeMessageByUserIdAndNoticeStatus(userId);
        return noticeDTO;
    }

    //자라어스 생성 시 스케줄링 테스트 용도
    @PostMapping("/scheduling")
    public void scheduling (@RequestParam Long jaraUsId){
        scheduleService.jaraUsScheduling(jaraUsRepository.findByJaraUsId(jaraUsId));
    }
}
