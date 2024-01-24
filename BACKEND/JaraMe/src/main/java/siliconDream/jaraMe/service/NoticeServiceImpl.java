package siliconDream.jaraMe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.BindTag;
import siliconDream.jaraMe.domain.*;
import siliconDream.jaraMe.dto.MissionCompleteNoticeDTO;
import siliconDream.jaraMe.dto.NoticeDTO;
import siliconDream.jaraMe.dto.ReactionDTO;
import siliconDream.jaraMe.dto.ReactionNoticeDTO;
import siliconDream.jaraMe.repository.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    private final ReactionRepository reactionRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final PointRepository pointRepository;
    private final JaraUsRepository jaraUsRepository;
    private final MissionPostRepository missionPostRepository;
    private final PointHistoryRepository pointHistoryRepository;

    public NoticeServiceImpl(ReactionRepository reactionRepository,
                             MissionHistoryRepository missionHistoryRepository,
                             PointRepository pointRepository,
                             JaraUsRepository jaraUsRepository,
                             MissionPostRepository missionPostRepository,
                             PointHistoryRepository pointHistoryRepository) {
        this.reactionRepository = reactionRepository;
        this.missionHistoryRepository = missionHistoryRepository;
        this.pointRepository = pointRepository;
        this.jaraUsRepository = jaraUsRepository;
        this.missionPostRepository = missionPostRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    public Optional<NoticeDTO> findNoticeMessageByUserIdAndNoticeStatus(Long userId) {
        NoticeDTO noticeDTO = new NoticeDTO();


        /**미션인증글에 달린 리액션에 대한 공지사항**/
        //해당 유저가 올린 미션 인증글들의 식별자를 얻음
        Optional<List<Long>> missionPostIds = missionPostRepository.findMissionPostIdByUser_UserId(userId);
        log.info("missionPostIds:{}", missionPostIds.get()); //ok
        //해당 유저가 올린 미션 인증글들이 존재한다면,
        if (missionPostIds.isPresent()) {
            List<ReactionNoticeDTO> reactionNoticeDTOs = new ArrayList<>();
            //개별 미션 인증글에 달린 리액션 타입들을 모두 수집
            for (Long oneMissionPostId : missionPostIds.get()) {

                log.info("oneMissionPostId:{}", oneMissionPostId); //ok
                Optional<List<String>> reactionTypes = reactionRepository.findReactionTypeByMissionPost_MissionPostId(oneMissionPostId);
                log.info("reactionTypes:{}", reactionTypes.get()); //ok

                //개별 미션 인증글에 달린 리액션들이 존재한다면 ,
                if (reactionTypes.isPresent()) {
                    int like = 0;
                    int good = 0;
                    int smile = 0;

                    //해당 미션 인증글에 달린 리액션 개수 통계를 dto형태로 구성.


                    for (String oneReaction : reactionTypes.get()) {

                        if (oneReaction.equals("like")) {
                            like++;
                        } else if (oneReaction.equals("good")) {
                            good++;
                        } else if (oneReaction.equals("smile")) {
                            smile++;
                        }
                        log.info("oneReaction:{}", oneReaction); //ok
                        log.info("like:{}", like);
                        log.info("good:{}", good);
                        log.info("smile:{}", smile);

                    }
                    ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO(oneMissionPostId,
                            missionPostRepository.findByMissionPostId(oneMissionPostId).getTextTitle(),
                            like,
                            good,
                            smile);
                    log.info("reactionNoticeDTO.getGood:{}", reactionNoticeDTO.getGood());
                    //reacionNoticeDTOs에 방금 구성한 reationNoticeDTO를 추가.
                    reactionNoticeDTOs.add(reactionNoticeDTO);
                    log.info("reactionNoticeDTOs:{}", reactionNoticeDTOs);

                }else if (reactionTypes.isEmpty()){;}


            }noticeDTO.setReactionNoticeDTO(Optional.ofNullable(reactionNoticeDTOs));
        }



    /**
     * 미션완주 포인트 지급에 대한 공지사항
     **/
    //해당 유저의 포인트 지급 기록 중 미션완주를 했을 때 지급되는 포인트 지급-공지사항을 전달한 적 없는 기록을 가져오기
    Optional<List<PointHistory>> pointHistories = pointHistoryRepository.findByUser_UserIdAndNotice(userId, false);
    //해당 유저가 미션완주 후 포인트를 지급받았는데, 전달한 적 없는 기록이 있다면,
        if(pointHistories.isPresent())

    {
            /*
             //미션 이름 , 자라어스 이름, 기간, 지급 포인트
            private Long earnPoint;
            private String missionName;
            private String jaraUsName;
            private String period;
            */
        List<PointHistory> pointHistoryList = pointHistories.get();
        List<MissionCompleteNoticeDTO> missionCompleteNoticeDTOList = new ArrayList<>();
        for (PointHistory one : pointHistoryList) {
            String jaraUsIdPart = one.getTask().replace("missionComplete ", "");
            Long jaraUsId = Long.parseLong(jaraUsIdPart);

            JaraUs jaraUs = jaraUsRepository.findByJaraUsId(jaraUsId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            String startDateString = jaraUs.getStartDate().format(formatter);
            String endDateString = jaraUs.getEndDate().format(formatter);

            MissionCompleteNoticeDTO missionCompleteNoticeDTO = new MissionCompleteNoticeDTO(one.getChangeAmount(),
                    jaraUs.getMissionName(),
                    jaraUs.getJaraUsName(),
                    startDateString + "~" + endDateString);
            missionCompleteNoticeDTOList.add(missionCompleteNoticeDTO);
        }
        noticeDTO.setMissionCompleteNoticeDTO(Optional.of(missionCompleteNoticeDTOList));
    }

    Optional<NoticeDTO> noticeDTOOptional = Optional.of(noticeDTO);
        return noticeDTOOptional;

}
/*
    public ReactionNoticeDTO findTotalNewReactionNumberByUserId(Long userId){
        ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO();
        return reactionNoticeDTO;
    }
*/

}
