package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.MissionCommentDTO;

public interface CommentService {

<<<<<<< HEAD
    void addComment(Long userId, MissionCommentDTO missionCommentDTO);
=======
    boolean addComment(Long userId, MissionCommentDTO missionCommentDTO);
>>>>>>> develop_hm

    String deleteComment(Long commentId, Long userId);

}
