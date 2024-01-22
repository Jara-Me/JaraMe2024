package siliconDream.jaraMe.service;


import siliconDream.jaraMe.dto.MissionFinishNoticeDTO;

public interface NoticeService {
    MissionFinishNoticeDTO findNoticeMessageByUserIdAndNoticeStatus(Long userId);
}
