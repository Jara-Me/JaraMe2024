package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MissionPostServiceImpl implements MissionPostService {
    private final MissionPostRepository missionPostRepository;
    private final ScheduleRepository scheduleRepository;
    private final DailyMissionRepository dailyMissionRepository;
    private final PointRepository pointRepository;
    private final MissionHistoryRepository missionHistoryRepository;

    @Autowired
    public MissionPostServiceImpl(MissionPostRepository missionPostRepository,
                                  ScheduleRepository scheduleRepository,
                                  DailyMissionRepository dailyMissionRepository,
                                  PointRepository pointRepository,
                                  MissionHistoryRepository missionHistoryRepository) {
        this.missionPostRepository = missionPostRepository;
        this.scheduleRepository = scheduleRepository;
        this.dailyMissionRepository = dailyMissionRepository;
        this.pointRepository = pointRepository;
        this.missionHistoryRepository = missionHistoryRepository;
    }

    //미션 인증글 작성
    public Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPost) {

        //TODO: 만약 오늘 해당 미션에 대해 인증글을 하나 올렸다면 못올리도록 하기
        //TODO: 작성에 대한 규칙?

        //미션 인증글 저장하고 저장된 내용 받아오기
        Optional<MissionPost> savedMissionPostOptional = missionPostRepository.saveMissionPost(missionPost);
        MissionPost savedMissionPost = savedMissionPostOptional.get();

        //저장된 미션 인증글의 식별자(missionPostId) 얻기
        Long missionPostId = savedMissionPost.getMissionPostId();

        Long jaraUsId = savedMissionPost.getJaraUs().getJaraUsId();
        Long userId = savedMissionPost.getUser().getUserId();
        // 오늘의 미션 중 얼마나 완료했는지 반영
        dailyMissionFinish(userId, jaraUsId); //어떤 유저인지, 오늘의 미션 중 어떤 미션(그룹)을 완료했는지 전달

        //게시글 조회에 필요한 정보 DTO 반환
        return getMissionPostDetails(missionPostId);
    }


    //미션 인증글 조회
    public Optional<GetMissionPostDTO> getMissionPostDetails(Long missionPostId) {
        //레코드 찾기
        //missionPostRepository.findByMissionPostId(missionPostId); //인증글 레코드만 전달받을 수 있음.
        GetMissionPostDTO getMissionPostDTO = new GetMissionPostDTO();
        getMissionPostDTO = missionPostRepository.findByMissionPostIdWithCommentsAndReactions(missionPostId); //댓글,리액션까지 전달.

        return Optional.ofNullable(getMissionPostDTO);
    }


    //오늘의 미션 완료
    //미션 인증글 등록 시 호출됨. => 미션 인증 여부를 업데이트한 후,
    //'오늘의 미션' 전체를 인증했는지 여부를 확인해서 모두 True인 경우 포인트 부여.
    public boolean dailyMissionFinish(Long userId, Long jaraUsId) {
        boolean result = false;
        // dailyMission 테이블에서 매개변수로 전달받은 userId로 필터링한 뒤,
        //       매개변수로 전달받은 jaraUsId로 필터링한 레코드의 dailyMissionResult F->T로 업데이트
        dailyMissionRepository.updateDailyMissionStatus(userId, jaraUsId);

        //LocalDate today = LocalDate.now();
        //오늘의 미션 전부 완료했는지 알아보는 부분
        List<DailyMission> dailyMissionList = dailyMissionRepository.findByUser_UserId(userId);


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
            result = pointRepository.updateDailyMission(userId, earnedPoint);
        }


        return result;//예외처리 하기
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
            String textTitle=missionPostDTO.getTextTitle();
            String textContent = missionPostDTO.getTextContent();
            String imageContent = missionPostDTO.getImageContent();

            missionPostRepository.updateMissionPostByMissionPostId(missionPostId, display, anonymous, textTitle, textContent, imageContent);
            return "수정되었습니다.";
        } /*else if (!missionHistoryMissionPostIds.contains(missionPostId)) { //해당 유저가 작성한 글은 맞지만 오늘 미션이 아니라면
            // 추가해야하는 내용 : display와 anonymous 설정이 변경되었는지
            missionPostRepository.updateMissionPostMetaDataByMissionPostId(missionPostId, display, anonymous); //작성 제목,내용은 수정못하고 공개/익명 여부만 수정 가능하도록
            return "수정가능한 날짜가 지나 공개/익명 여부만 수정 가능합니다. ";
            return false;*/
        else {return "수정에 실패했습니다.";}
    }




    //미션인증글 삭제

}
