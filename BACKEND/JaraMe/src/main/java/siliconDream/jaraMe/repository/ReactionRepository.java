package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.Comment;
import siliconDream.jaraMe.domain.Reaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {
    Optional<Reaction> findByReactionId(Long reactionId);

    Optional<Reaction> findReactionByMissionPost_MissionPostIdAndUser_UserId(Long missionPostId, Long userId);


    //리액션 안단 경우에는 ? => optional?
    @Query("SELECT r.reactionType FROM Reaction r WHERE r.missionPost.missionPostId = :missionPostId AND r.user.userId = :userId")
    Optional<String> findReactionTypeByMissionPost_MissionPostIdAndUser_UserId(Long missionPostId, Long userId);

}
