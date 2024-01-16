package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;

import java.util.Optional;

@Repository
public interface MissionPostRepository extends JpaRepository<MissionPost, Long> {

    MissionPost findByMissionPostId(Long missionPostId);

    //TODO : 미션인증글 작성
    default Optional<MissionPost> saveMissionPost(MissionPostDTO missionPostDTO) {
        //객체 형성
        MissionPost missionPost = new MissionPost();

        //컬럼값 설정
        missionPost.setUser(missionPost.getUser());
        missionPost.setGroup(missionPost.getGroup());

        missionPost.setDisplay(missionPost.getDisplay());
        missionPost.setAnonymous(missionPost.getAnonymous());
        missionPost.setPostDateTime(missionPost.getPostDateTime());

        missionPost.setTextTitle(missionPost.getTextTitle());
        missionPost.setTextContent(missionPost.getTextContent());
        missionPost.setImageContent(missionPost.getImageContent());


        save(missionPost);

        //id(missionPostId,기본키) 반환받기
        Long savedMissionPostId = missionPost.getMissionPostId();

        MissionPost savedMissionPost = new MissionPost();
        savedMissionPost = findByMissionPostId(savedMissionPostId);
        return Optional.ofNullable(savedMissionPost);
    }














    //미션 인증글 조회
    @Query("SELECT new siliconDream.jaraMe.dto.GetMissionPostDTO(" +
            "mp.missionPostId, mp.textTitle, mp.textContent, mp.imageContent, mp.postDateTime," +
            "u.nickname, u.profileImage, " +
            "c.commentId, c.commentContent, c.commentDate, " +
            "cu.nickname as commentUserNickname, cu.profileImage as commentUserProfileImage, " +
            "r.reactionId, r.reactionType, " +
            "ru.nickname as reactionUserNickname, ru.profileImage as reactionUserProfileImage, " +
            "mp.comment as CommentDTO, " +
            "mp.reaction as ReactionDTO) " +
            "FROM MissionPost mp " +
            "LEFT JOIN mp.comment c " +
            "LEFT JOIN c.user cu " +
            "LEFT JOIN mp.reaction r " +
            "LEFT JOIN r.user ru " +
            "LEFT JOIN mp.user u " +
            "WHERE mp.missionPostId = :missionPostId OR c.missionPost.missionPostId = :missionPostId OR r.missionPost.missionPostId = :missionPostId")
    GetMissionPostDTO findByMissionPostIdWithCommentsAndReactions(@Param("missionPostId") Long missionPostId);



}
