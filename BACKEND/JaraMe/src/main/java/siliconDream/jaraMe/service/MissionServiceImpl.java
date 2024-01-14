package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.repository.MissionPostRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MissionServiceImpl implements MissionService {
    private final MissionPostRepository missionPostRepository;

    @Autowired
    public MissionServiceImpl(MissionPostRepository missionPostRepository) {
        this.missionPostRepository = missionPostRepository;
    }

    //TODO: 미션 인증글 작성
    public Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPost) {

        //미션 인증글 저장
        Optional<MissionPost> savedMissionPostOptional = missionPostRepository.saveMissionPost(missionPost);
        MissionPost savedMissionPost = savedMissionPostOptional.get();

        Long missionPostId = savedMissionPost.getMissionPostId();

        Long groupId = savedMissionPost.getGroupId();
        Long userId = savedMissionPost.getUserId();
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

/*
    //TODO: 미션 완주
    public boolean missionFinish(Long userId, Long groupId) {
        //TODO : MissionFinishScheduler에서 가능한 경우 여기서 구현 x
        //포인트 지급

        return true;
    }
*/
    //TODO : 오늘의 미션 완료
    public boolean dailyMissionFinish(Long userId, Long groupId) {
        //TODO : dailyMission 테이블에서 매개변수로 전달받은 userId로 필터링한 뒤,
        //       매개변수로 전달받은 groupId로 필터링한 레코드의 dailyMissionResult F->T로 업데이트

        //TODO : dailyMission 테이블에서 userId로 필터링했을 때 dailyMissionResult 컬럼이 모두 T인 경우,
        //       레코드가 몇개인지 개수 세기
        //       그리고 레코드의 개수*3만큼의 포인트 지급

        //포인트 지급


        return true;
    }

    //TODO : 오늘의 미션 조회
    public DailyMissionDTO getDailyMission(Long userId, LocalDateTime todayDate) {
        DailyMissionDTO dailyMissionDTO = new DailyMissionDTO();
        //TODO: userId와 todayDate 전달받기

        //TODO: missionSchedule 테이블에서 매개변수로 받은 userId 필터링한 레코드 찾아서, missionScheduleId 추출하기

        //TODO:missionScheduleDate 테이블에서 매개변수로 받은 todayDate 와 missionScheduleId로 필터링해서 '오늘의 미션' 알아내기

        //TODO: DTO 구성해서 반환하기 (dailyMissionId 혹은 DailyMission 테이블 자체도 포함하기 )
        return dailyMissionDTO;
    }

    //public void setDailyMission(){ //전체 유저 모두, 일괄 처리 => service없이 scheduler에 구현 .


}
