package siliconDream.jaraMe.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.ReturnMissionPostDTO;

import java.time.LocalDateTime;
import java.util.Optional;


public interface MissionRepository extends JpaRepository<MissionPost, Long> {

    Optional<MissionPost> findByMissionPostId(Long missionPostId);

    //TODO: 미션인증글 작성
    default Optional<MissionPost> saveMissionPost(MissionPostDTO missionPostDTO) {
        //객체 형성
        MissionPost missionPost = new MissionPost();

        //컬럼값 설정
        missionPost.setUserId(missionPost.getUserId());
        missionPost.setGroupId(missionPost.getGroupId());

        missionPost.setDisplay(missionPost.isDisplay());
        missionPost.setAnonymous(missionPost.isAnonymous());
        missionPost.setPostDateTime(missionPost.getPostDateTime());

        missionPost.setTextTitle(missionPost.getTextTitle());
        missionPost.setTextContent(missionPost.getTextContent());
        missionPost.setImageContent(missionPost.getImageContent());


        save(missionPost);

        //id(missionPostId,기본키) 반환받기
        Long savedMissionPostId = missionPost.getId();

        MissionPost savedMissionPost = new MissionPost();
        savedMissionPost = findByMissionPostId(savedMissionPostId).get();
       return Optional.ofNullable(savedMissionPost);
    }

/*
    //인증글 조회
    default Optional<MissionPost> getMissionPost(Long missionPostId) {
        ReturnMissionPostDTO returnMissionPostDTO = new ReturnMissionPostDTO();

        MissionPost missionPost =find(MissionPost.class, missionPostId);

        //userId로 얻는 값들
        Long userId = missionPost.getUserId();
        User user = entityManager.find(User.class, userId);

        //DTO구성
        returnMissionPostDTO.setUserProfileImage(user.getProfileImage());
        returnMissionPostDTO.setNickname(user.getNickname());
        returnMissionPostDTO.setDateTime(missionPost.getPostDateTime());
        returnMissionPostDTO.setTitle(missionPost.getTextTitle());
        returnMissionPostDTO.setTextContent(missionPost.getTextContent());
        returnMissionPostDTO.setImageContent(missionPost.getImageContent());

        //댓글,인증글

        return Optional<returnMissionPostDTO>;

    }*/

}
