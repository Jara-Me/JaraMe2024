package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.MissionCommentDTO;

public interface CommentService {

    void addComment(Long userId, MissionCommentDTO missionCommentDTO);

    void deleteComment(Long commentId, Long userId);

}
