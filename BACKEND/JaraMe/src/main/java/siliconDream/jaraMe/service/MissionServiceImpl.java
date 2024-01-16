package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.DailyMission;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.repository.DailyMissionRepository;
import siliconDream.jaraMe.repository.MissionPostRepository;
import siliconDream.jaraMe.repository.PointRepository;
import siliconDream.jaraMe.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MissionServiceImpl implements MissionService {
    private final MissionPostRepository missionPostRepository;
    private final ScheduleRepository scheduleRepository;
    private final DailyMissionRepository dailyMissionRepository;
    private final PointRepository pointRepository;

    @Autowired
    public MissionServiceImpl(MissionPostRepository missionPostRepository, ScheduleRepository scheduleRepository, DailyMissionRepository dailyMissionRepository, PointRepository pointRepository) {
        this.missionPostRepository = missionPostRepository;
        this.scheduleRepository = scheduleRepository;
        this.dailyMissionRepository = dailyMissionRepository;
        this.pointRepository = pointRepository;
    }

    //TODO: 미션 인증글 작성
    public Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPost) {

        //미션 인증글 저장하고 저장된 내용 받아오기
        Optional<MissionPost> savedMissionPostOptional = missionPostRepository.saveMissionPost(missionPost);
        MissionPost savedMissionPost = savedMissionPostOptional.get();

        //저장된 미션 인증글의 식별자(missionPostId) 얻기
        Long missionPostId = savedMissionPost.getMissionPostId();


        Long groupId = savedMissionPost.getGroup().getGroupId();
        Long userId = savedMissionPost.getUser().getUserId();
        //TODO: 오늘의 미션 중 얼마나 완료했는지 반영
        dailyMissionFinish(userId, groupId); //어떤 유저인지, 오늘의 미션 중 어떤 미션(그룹)을 완료했는지 전달

        //게시글 조회에 필요한 정보 DTO 반환
        return getMissionPostDetails(missionPostId);
    }

    //TODO : 미션 인증글 조회
    public Optional<GetMissionPostDTO> getMissionPostDetails(Long missionPostId) {
        //레코드 찾기
        //missionPostRepository.findByMissionPostId(missionPostId); //인증글 레코드만 전달받을 수 있음.
        GetMissionPostDTO getMissionPostDTO = new GetMissionPostDTO();
        getMissionPostDTO = missionPostRepository.findByMissionPostIdWithCommentsAndReactions(missionPostId); //댓글,리액션까지 전달.


        return Optional.ofNullable(getMissionPostDTO);
    }

    //TODO : 오늘의 미션 완료
    //미션 인증글 등록 시 호출됨. => 미션 인증 여부를 업데이트한 후,
    // '오늘의 미션' 전체를 인증했는지 여부를 확인해서 모두 True인 경우 포인트 부여.
    public boolean dailyMissionFinish(Long userId, Long groupId) {
        boolean result=false;
        //TODO : dailyMission 테이블에서 매개변수로 전달받은 userId로 필터링한 뒤,
        //       매개변수로 전달받은 groupId로 필터링한 레코드의 dailyMissionResult F->T로 업데이트
        dailyMissionRepository.updateDailyMissionStatus(userId, groupId);

        //LocalDate today = LocalDate.now();
        //오늘의 미션 전부 완료했는지 알아보는 부분
        List<DailyMission> dailyMissionList = dailyMissionRepository.findByUserId(userId);


        //TODO : dailyMission 테이블에서 userId로 필터링했을 때 dailyMissionResult 컬럼이 모두 T인 경우,
        //       레코드가 몇개인지 개수 세기
        //       그리고 레코드의 개수*3만큼의 포인트 지급
        //전달받은 dailyMissionList의 dailyMissionStatus 컬럼이 모두 true
        boolean allTrue = dailyMissionList.stream()
                .allMatch(dailyMission -> dailyMission.isDailyMissionStatus());

        //모든 레코드의 컬럼 값이 true라면 포인트 지급
        if (allTrue) {
            Long taskNumber = (long) dailyMissionList.size();
            Long earnedPoint = taskNumber * 3;
            result = pointRepository.updateDailyMission(userId, earnedPoint);
        }



        return result;//예외처리 하기
}

    //TODO : 오늘의 미션 조회
    public DailyMissionDTO getDailyMission(Long userId, LocalDateTime todayDate) {
        DailyMissionDTO dailyMissionDTO = new DailyMissionDTO();
        //TODO: userId와 todayDate 전달받기
        //TODO: Schedule 테이블에서 매개변수로 받은 userId 필터링한 레코드 찾아서, scheduleId 추출하기


        //TODO:ScheduleDate 테이블에서 매개변수로 받은 todayDate 와 scheduleId로 필터링해서 '오늘의 미션' 알아내기

        //TODO: DTO 구성해서 반환하기 (dailyMissionId 혹은 DailyMission 테이블 자체도 포함하기 )
        return dailyMissionDTO;
    }

/*
    //TODO: 미션 완주
    public boolean missionFinish(Long userId, Long groupId) {
        //TODO : MissionFinishScheduler에서 가능한 경우 여기서 구현 x
        //포인트 지급

        return true;
    }
*/


    //public void setDailyMission(){ //전체 유저 모두, 일괄 처리 => service없이 scheduler에 구현 ?


}