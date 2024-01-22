package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siliconDream.jaraMe.dto.MissionFinishNoticeDTO;
import siliconDream.jaraMe.dto.ReactionNoticeDTO;
import siliconDream.jaraMe.service.MissionHistoryService;
import siliconDream.jaraMe.service.NoticeService;

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

    //로그아웃 상태였을 때 미션완주로 포인트 적립된 것이 있다면
    @GetMapping("/missionFinish")
    public MissionFinishNoticeDTO missionFinishNotice(Long userId){
        MissionFinishNoticeDTO missionFinishNoticeDTO=noticeService.findNoticeMessageByUserIdAndNoticeStatus(userId);
        return missionFinishNoticeDTO;
    }


    //리액션 통계 전달

    @GetMapping("/reaction")
    public ReactionNoticeDTO reactionNotice(Long userId){
        //MissionHistory테이블에서 해당 유저의 게시글 중 리액션 업데이트 정보를 전달
        ReactionNoticeDTO reactionNoticeDTO = missionHistoryService.findTotalNewReactionNumberByUserId(userId);
                //notice전달이 되지않은(false인 상태인 컬럼인 것이) like, good, smile 모두 각각 몇개인지 전달
        return reactionNoticeDTO;
    }
}
