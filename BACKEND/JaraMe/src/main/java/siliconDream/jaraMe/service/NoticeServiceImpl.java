package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dto.MissionFinishNoticeDTO;
import siliconDream.jaraMe.dto.ReactionNoticeDTO;

@Service
public class NoticeServiceImpl implements  NoticeService{

    public MissionFinishNoticeDTO findNoticeMessageByUserIdAndNoticeStatus(Long userId){
        MissionFinishNoticeDTO missionFinishNoticeDTO = new MissionFinishNoticeDTO();

        return missionFinishNoticeDTO;

    }

    public ReactionNoticeDTO findTotalNewReactionNumberByUserId(Long userId){
        ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO();
        return reactionNoticeDTO;
    }


}
