package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.MissionCommentDTO;

public interface CommentService {

    void addComment(MissionCommentDTO missionCommentDTO);

    void deleteComment(Long commentId, Long userId);

}
