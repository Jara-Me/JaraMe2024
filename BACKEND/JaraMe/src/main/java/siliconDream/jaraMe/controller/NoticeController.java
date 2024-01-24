package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siliconDream.jaraMe.dto.NoticeDTO;
import siliconDream.jaraMe.service.MissionHistoryService;
import siliconDream.jaraMe.service.NoticeService;

import java.util.Optional;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final MissionHistoryService missionHistoryService;

    public NoticeController(NoticeService noticeService,
                            MissionHistoryService missionHistoryService){
        this.noticeService=noticeService;
        this.missionHistoryService = missionHistoryService;
    }

    //미션완주,리액션통계
    @GetMapping("/get")
    public Optional<NoticeDTO> missionFinishNotice(@RequestParam Long userId){
        Optional<NoticeDTO> noticeDTO =noticeService.findNoticeMessageByUserIdAndNoticeStatus(userId);
        return noticeDTO;
    }


}
