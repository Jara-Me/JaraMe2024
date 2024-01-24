package siliconDream.jaraMe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.BindTag;
import siliconDream.jaraMe.domain.*;
import siliconDream.jaraMe.dto.*;
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
            //개별 미션 인증글에 달린 리액션통계
            for (Long oneMissionPostId : missionPostIds.get()) {
                int smile=0;
                int good=0;
                int like=0;
                log.info("oneMissionPostId:{}", oneMissionPostId); //ok
                Optional<List<Object[]>> reactionCountDTOs = reactionRepository.findByMissionPost_MissionPostId(oneMissionPostId);
                if (reactionCountDTOs.isPresent()) {
                    for (Object[] object : reactionCountDTOs.get()) {
                        for (int i = 0; i < 3; i++) {
                            if (object[0].equals("smile")) {
                                smile = ((Number) object[1]).intValue();
                            }
                            else if (object[0].equals("like")) {
                                like = ((Number) object[1]).intValue();
                            }else if (object[0].equals("good")) {
                                good = ((Number) object[1]).intValue();
                            }


                    }
                }

                    ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO(oneMissionPostId,
                            missionPostRepository.findByMissionPostId(oneMissionPostId).getTextTitle(),
                            like,
                            good,
                            smile);

                    //reacionNoticeDTOs에 방금 구성한 reationNoticeDTO를 추가.
                    reactionNoticeDTOs.add(reactionNoticeDTO);
                    log.info("reactionNoticeDTOs:{}", reactionNoticeDTOs);
                    reactionRepository.updateNoticeByMissionPost_MissionPostId(oneMissionPostId, true);


                } else if (reactionCountDTOs.get().size() == 0) {
                    ;
                }


            }
            noticeDTO.setReactionNoticeDTO(Optional.ofNullable(reactionNoticeDTOs));
        }


        /**
         * 미션완주 포인트 지급에 대한 공지사항
         **/
        //해당 유저의 포인트 지급 기록 중 미션완주를 했을 때 지급되는 포인트 지급-공지사항을 전달한 적 없는 기록을 가져오기
        Optional<List<PointHistory>> pointHistories = pointHistoryRepository.findByUser_UserIdAndNotice(userId, false);
        log.info("log: pointHistories:{}", pointHistories.get());

        //해당 유저가 미션완주 후 포인트를 지급받았는데, 전달한 적 없는 기록이 있다면,
        if (pointHistories.get().size() != 0) {
            /*
             //미션 이름 , 자라어스 이름, 기간, 지급 포인트
            private Long earnPoint;
            private String missionName;
            private String jaraUsName;
            private String period;
            */

            List<MissionCompleteNoticeDTO> missionCompleteNoticeDTOs = new ArrayList<>();
            for (PointHistory one : pointHistories.get()) {
                log.info("log: one.getTask():{}", one.getTask());
                String jaraUsIdPart = one.getTask().replace("missionComplete ", "");
                log.info("log: jaraUsIdPart:{}", jaraUsIdPart);
                Long jaraUsId = Long.parseLong(jaraUsIdPart);

                JaraUs jaraUs = jaraUsRepository.findByJaraUsId(jaraUsId);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
                String startDateString = jaraUs.getStartDate().format(formatter);
                String endDateString = jaraUs.getEndDate().format(formatter);

                MissionCompleteNoticeDTO missionCompleteNoticeDTO = new MissionCompleteNoticeDTO(one.getChangeAmount(),
                        jaraUs.getMissionName(),
                        jaraUs.getJaraUsName(),
                        startDateString + " ~ " + endDateString);
                one.setNotice(true);
                pointHistoryRepository.save(one);
                missionCompleteNoticeDTOs.add(missionCompleteNoticeDTO);


            }
            noticeDTO.setMissionCompleteNoticeDTO(Optional.of(missionCompleteNoticeDTOs));
        }


        return Optional.of(noticeDTO);

    }
/*
    public ReactionNoticeDTO findTotalNewReactionNumberByUserId(Long userId){
        ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO();
        return reactionNoticeDTO;
    }
*/

}
