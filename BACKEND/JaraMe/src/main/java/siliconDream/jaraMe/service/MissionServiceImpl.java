package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.ReturnMissionPostDTO;
import siliconDream.jaraMe.repository.MissionRepository;

import java.util.Optional;

public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    //TODO: 미션 인증글 작성
    //TODO: 반환값 바꾸기
    public boolean missionPost(MissionPostDTO missionPost) {
        /*
    Long userId = missionPost.getUserId();
    Long groupId = missionPost.getGroupId();
    LocalDateTime dateTime = missionPost.getDateTime();
    boolean display = missionPost.getDisplay();
    boolean anonymous = missionPost.getAnonymous();
    String title = missionPost.getTitle();
    String textContent = missionPost.getTextContent();
    String imageContent = missionPost.getImageContent();
*/
        //미션 인증글 저장
        Optional<MissionPost> savedMissionPostOptional = missionRepository.saveMissionPost(missionPost);
        MissionPost savedMissionPost = savedMissionPostOptional.get();

        //DTO 형성
        ReturnMissionPostDTO returnMissionPostDTO = new ReturnMissionPostDTO();
        //TODO: 컬럼값 설정

        //Optional<ReturnMissionPostDTO> returnMissionPostDTO =missionRepository.saveMissionPost(missionPost);


        return true;
    }
}
