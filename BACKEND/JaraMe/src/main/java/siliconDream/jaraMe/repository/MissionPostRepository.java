package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionPostRepository extends JpaRepository<MissionPost, Long> {

    MissionPost findByMissionPostId(Long missionPostId);


    MissionPost save(MissionPost missionPost);


    //미션 인증글 조회

    Optional<MissionPost> findMissionPostByMissionPostId(@Param("missionPostId") Long missionPostId);




    @Modifying
    @Query("UPDATE MissionPost mp SET mp.display = :display, mp.anonymous = :anonymous, mp.textTitle = :textTitle, mp.textContent = :textContent, mp.imageContent =:imageContent " +
            "WHERE mp.missionPostId = :missionPostId")
    void updateMissionPostByMissionPostId(Long missionPostId, boolean display, boolean anonymous, String textTitle, String textContent, String imageContent);

}
