package siliconDream.jaraMe.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.Comment;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.CommentDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class MissionPostServiceImpl implements MissionPostService {
    private final MissionPostRepository missionPostRepository;
    private final ScheduleRepository scheduleRepository;
    private final DailyMissionRepository dailyMissionRepository;
    private final PointRepository pointRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final UserRepository userRepository;
    private final JaraUsRepository jaraUsRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    @Autowired
    public MissionPostServiceImpl(MissionPostRepository missionPostRepository,
                                  ScheduleRepository scheduleRepository,
                                  DailyMissionRepository dailyMissionRepository,
                                  PointRepository pointRepository,
                                  MissionHistoryRepository missionHistoryRepository,
                                  UserRepository userRepository,
                                  JaraUsRepository jaraUsRepository,
                                  CommentRepository commentRepository,
                                  ReactionRepository reactionRepository) {
        this.missionPostRepository = missionPostRepository;
        this.scheduleRepository = scheduleRepository;
        this.dailyMissionRepository = dailyMissionRepository;
        this.pointRepository = pointRepository;
        this.missionHistoryRepository = missionHistoryRepository;
        this.userRepository = userRepository;
        this.jaraUsRepository = jaraUsRepository;
        this.commentRepository = commentRepository;
        this.reactionRepository = reactionRepository;
    }

    //미션 인증글 작성
    //TODO: 만약 오늘 해당 미션에 대해 인증글을 하나 올렸다면 못올리도록 하기
    //TODO: 작성에 대한 규칙?
    public String missionPost(MissionPostDTO missionPostDTO, Long userId) {


        MissionPost missionPost = new MissionPost();
        missionPost.setAnonymous(missionPostDTO.isAnonymous());
        missionPost.setDisplay(missionPostDTO.isDisplay());
        missionPost.setTextTitle(missionPostDTO.getTextTitle());
        missionPost.setTextContent(missionPostDTO.getTextContent());
        missionPost.setImageContent(missionPostDTO.getImageContent());
        missionPost.setPostDateTime(missionPostDTO.getPostDateTime());
        missionPost.setUser(userRepository.findByUserId(userId));

        Long jaraUsId = missionPostDTO.getJaraUsId();
        missionPost.setJaraUs(jaraUsRepository.findByJaraUsId(jaraUsId));

        //TODO: 저장 => 여길 Optional로 바꿔서 isPresent로 성공/실패여부 반영하기
        MissionPost savedMissionPost = missionPostRepository.save(missionPost);


        //TODO: dailyMission테이블에도 missionPostId 저장하기
        Long savedMissionPostId = savedMissionPost.getMissionPostId();
//
//        dailyMissionFinish(userId, jaraUsId, savedMissionPost, savedMissionPost.getPostDateTime());
        return "저장이 완료되었습니다.";
    }


    //미션 인증글 조회
    //TODO: 해당 missionId가 없다면?
    //TODO: 비공개된 게시글이거나 삭제된 게시글이라면 ?
    public GetMissionPostDTO getMissionPostDetails(Long missionPostId, Long userId) {
        //레코드 찾기
        //missionPostRepository.findByMissionPostId(missionPostId); //인증글 레코드만 전달받을 수 있음.
        Optional<MissionPost> missionPostOptional = missionPostRepository.findMissionPostByMissionPostId(missionPostId); //댓글,리액션까지 전달.
        //TODO: 조회하는 사람이 해당 인증글에 단 리액션 정보 전달?
        Optional<String> reactionTypeOptional = reactionRepository.findReactionTypeByMissionPost_MissionPostIdAndUser_UserId(missionPostId, userId);

        Optional<List<CommentDTO>> commentOptional = commentRepository.findCommentByMissionPost_MissionPostId(missionPostId);

        return makeGetMissionPostDTO(missionPostOptional, reactionTypeOptional, commentOptional);

    }


    //미션 인증글 조회를 위한 DTO 형성
    public GetMissionPostDTO makeGetMissionPostDTO(Optional<MissionPost> missionPostOptional, Optional<String> reactionTypeOptional, Optional<List<CommentDTO>> commentOptional) {
        GetMissionPostDTO getMissionPostDTO = new GetMissionPostDTO();

        missionPostOptional.ifPresent(missionPost -> {
            getMissionPostDTO.setMissionPostId(missionPost.getMissionPostId());
            getMissionPostDTO.setTextTitle(missionPost.getTextTitle());
            getMissionPostDTO.setTextContent(missionPost.getTextContent());
            getMissionPostDTO.setImageContent(missionPost.getImageContent());
            getMissionPostDTO.setPostDateTime(missionPost.getPostDateTime());
            getMissionPostDTO.setNickname(missionPost.getUser().getNickname());
            getMissionPostDTO.setProfileImage(missionPost.getUser().getProfileImage());
        });

        reactionTypeOptional.ifPresent(getMissionPostDTO::setReactionType);

        commentOptional.ifPresent(getMissionPostDTO::setCommentDTO);

        return getMissionPostDTO;
    }
public String dailyMissionUpdate(Long userId,Long jaraUsId, Long missionPostId){
        missionPostRepository.findByMissionPostId(missionPostId);
    //저장한 missionPostId가 오늘의 미션 중 어느 미션을 인증하고자했던 것인지 파악
    // dailyMission 테이블에서 dailyMissionId로 레코드를 찾은 다음,
    Long dailyMissionId = dailyMissionRepository.findDailyMissionIdByUser_UserIdAndJaraUs_JaraUsId(userId, jaraUsId);
    log.info("MissionPostController-dailyMissionUpdate-dailyMissionId: {}",dailyMissionId);
    MissionPost savedMissionPost = missionPostRepository.findByMissionPostId(missionPostId);
dailyMissionFinish(dailyMissionId,savedMissionPost,savedMissionPost.getPostDateTime());
return "";
}

    //오늘의 미션 완료
//미션 인증글 등록 시 호출됨. => 미션 인증 여부를 업데이트한 후, '오늘의 미션' 전체를 인증했는지 여부를 확인해서 모두 True인 경우 포인트 부여.
    @Transactional
    public void dailyMissionFinish(Long dailyMissionId, MissionPost savedMissionPost, LocalDateTime postedDateTime) {

         // dailyMissionResult를 true로 설정하고,
        // savedMissionPostId,
        // postedDateTime 값으로 업데이트한다.

        dailyMissionRepository.updateDailyMissionStatus(true,dailyMissionId, savedMissionPost, postedDateTime);


        /**테스트 목적**//*
        DailyMission testDailyMission = dailyMissionRepository.findDailyMissionByDailyMissionId(dailyMissionId);


        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getDailyMissionId: {}",testDailyMission.getDailyMissionId());
        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getMissionPostId: {}",testDailyMission.getMissionPost().getMissionPostId());

        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getJaraUs().getJaraUsId(): {}",testDailyMission.getJaraUs().getJaraUsId());
        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getUser().getUserId(): {}",testDailyMission.getUser().getUserId());
        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getScheduleDate();: {}",     testDailyMission.getScheduleDate());
        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getDoneDateTime();: {}",testDailyMission.getDoneDateTime());
        log.info("MissionPostController-dailyMissionFinish-testDailyMission.getDailyMissionResult();: {}",testDailyMission.isDailyMissionResult());
*/
        // TODO: 오늘의 미션 전부 완료했는지 알아보는 부분
      /*  List<DailyMission> dailyMissionList = dailyMissionRepository.findByUser_UserId(userId);


        // dailyMission 테이블에서 userId로 필터링했을 때 dailyMissionResult 컬럼이 모두 T인 경우,
        //       레코드가 몇개인지 개수 세기
        //       그리고 레코드의 개수*3만큼의 포인트 지급
        //전달받은 dailyMissionList의 dailyMissionStatus 컬럼이 모두 true
        boolean allTrue = dailyMissionList.stream()
                .allMatch(dailyMission -> dailyMission.isDailyMissionResult());

        //모든 레코드의 컬럼 값이 true라면 포인트 지급
        if (allTrue) {
            int taskNumber = dailyMissionList.size();
            int earnedPoint = taskNumber * 3;
            pointRepository.updateDailyMission(userId, earnedPoint);
        }
        //포인트 지급에 대한 return string 해야할 것같음! 수정 예정
*/
    }


    //미션에 참여한 유저들의 참여율 알아내기 => 스케줄링 구현 후에 할 수 있을 듯.
    public int missionParticipationRate(Long userId, Long jaraUsId) {

        //인증해야하는 날짜 전체 알아내기
        Set<LocalDate> totalDates = scheduleRepository.findScheduleDateByJaraUsId(jaraUsId);
        int totalNum = totalDates.size();//총 인증해야하는 횟수
        int postNum = 0; //실제로 인증한 횟수

        //해당 유저가 인증한 날짜들 알아내기
        Set<LocalDate> postedDates = missionHistoryRepository.findMissionDateByUser_UserIdAndJaraUs_JaraUsId(userId, jaraUsId);
        int result = 0;

        for (LocalDate oneOfTotal : totalDates) {
            if (postedDates.contains(oneOfTotal)) {
                postNum++;
            }
        }

        if (postNum == totalNum) {
            result = 50;
        } else if (postNum < totalNum && postNum >= totalNum * (2 / 3)) {
            result = 20;
        } else if (postNum < totalNum * (2 / 3) && postNum >= totalNum * (1 / 3)) {
            result = 10;
        } else if (postNum < totalNum * (1 / 3)) {
            result = 0;
        } else {
            result = -1; //에러에 해당 }


        }

        return result;
    }

    //미션인증글 수정
//TODO: 오늘=> 내용과 공개/익명 정보 모두 수정 가능
//TODO: 오늘이 아닌 경우 => 공개/익명 정보만 수정 가능
    public String updateMissionPost(Long missionPostId, MissionPostDTO missionPostDTO, Long userId, LocalDate todayDate) {
        //해당 유저의 '오늘의 미션' 중 수정하려는 미션인증글 식별자가 있는지 찾기 (즉, 오늘 올린 미션인증글인지 확인하기)
        List<Long> dailyMissionPostIds = dailyMissionRepository.findMissionPostIdsByUser_UserId(userId);
        List<Long> missionHistoryMissionPostIds = missionHistoryRepository.findMissionPostIdsByUser_UserId(userId);

        //오늘 올린 미션인증글 식별자들 중 수정하고자하는 미션인증글 식별자가 있다면 (즉, 오늘 올린 미션인증글이 맞다면)
        if (dailyMissionPostIds.contains(missionPostId)) {

            //dto에서 값을 꺼내서 전달 (수정할 값)
            boolean display = missionPostDTO.isDisplay();
            boolean anonymous = missionPostDTO.isAnonymous();

            //인증 게시글 본문 (제목,내용,첨부이미지)
            String textTitle = missionPostDTO.getTextTitle();
            String textContent = missionPostDTO.getTextContent();
            String imageContent = missionPostDTO.getImageContent();

            missionPostRepository.updateMissionPostByMissionPostId(missionPostId, display, anonymous, textTitle, textContent, imageContent);
            return "수정되었습니다.";
        } /*else if (!missionHistoryMissionPostIds.contains(missionPostId)) { //해당 유저가 작성한 글은 맞지만 오늘 미션이 아니라면
            // 추가해야하는 내용 : display와 anonymous 설정이 변경되었는지
            missionPostRepository.updateMissionPostMetaDataByMissionPostId(missionPostId, display, anonymous); //작성 제목,내용은 수정못하고 공개/익명 여부만 수정 가능하도록
            return "수정가능한 날짜가 지나 공개/익명 여부만 수정 가능합니다. ";
            return false;*/ else {
            return "수정에 실패했습니다.";
        }
    }


    //미션인증글 삭제

}
