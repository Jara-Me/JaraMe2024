package siliconDream.jaraMe.service;

public interface ReactionService {

    void addReaction(Long missionPostId,String reactionType, Long userId);
    void deleteReaction(Long missionPostId,String reactionType, Long userId);
}
