package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.repository.MissionPostRepository;

import java.util.Optional;
@Service
public class MissionServiceImpl implements MissionService{
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

        //게시글 조회에 필요한 정보 DTO 반환
        return getMissionPostDetails(missionPostId);
    }

    public Optional<GetMissionPostDTO> getMissionPostDetails(Long missionPostId){
        //레코드 찾기
        //missionPostRepository.findByMissionPostId(missionPostId); //인증글 레코드만 전달받을 수 있음.
        GetMissionPostDTO getMissionPostDTO = new GetMissionPostDTO();
        getMissionPostDTO = missionPostRepository.findByMissionPostIdWithCommentsAndReactions(missionPostId); //댓글,리액션까지 전달.



        return Optional.ofNullable(getMissionPostDTO);
    }
}
