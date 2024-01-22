package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dto.ReactionNoticeDTO;

@Service
public class MissionHistoryServiceImpl implements MissionHistoryService {

    public ReactionNoticeDTO findTotalNewReactionNumberByUserId(Long userId){
        ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO();

        return reactionNoticeDTO;
    }
}
