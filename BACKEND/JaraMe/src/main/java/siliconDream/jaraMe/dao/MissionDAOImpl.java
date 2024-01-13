package siliconDream.jaraMe.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.ReturnMissionPostDTO;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository

public class MissionDAOImpl implements MissionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //TODO: 미션인증글 작성
    @Override
    @Transactional
    public Optional<ReturnMissionPostDTO> saveMissionPost(Long userId, Long groupId, LocalDateTime dateTime, boolean display, boolean anonymous, String title, String textContent, String imageContent) {
        //객체 형성
        MissionPost missionPost = new MissionPost();

        //컬럼값 설정
        missionPost.setUserId(userId);
        missionPost.setGroupId(groupId);

        missionPost.setDisplay(display);
        missionPost.setAnonymous(anonymous);
        missionPost.setPostDateTime(dateTime);

        missionPost.setTextTitle(title);
        missionPost.setTextContent(textContent);
        missionPost.setImageContent(imageContent);

        //영속화(persist)로 db반영
        entityManager.persist(missionPost);

        //id(missionPostId,기본키) 반환받기
        Long savedMissionPostId = missionPost.getId();

        //반환할 dto 형성하기
        ReturnMissionPostDTO returnMissionPostDTO = new ReturnMissionPostDTO();
        returnMissionPostDTO = getMissionPost(savedMissionPostId);
        return Optional.ofNullable(returnMissionPostDTO);
    }


    //TODO: 인증글 조회
    @Override
    @Transactional
    public ReturnMissionPostDTO getMissionPost(Long missionPostId) {
        ReturnMissionPostDTO returnMissionPostDTO = new ReturnMissionPostDTO();

        MissionPost missionPost = entityManager.find(MissionPost.class, missionPostId);

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

        return returnMissionPostDTO;

    }

}
