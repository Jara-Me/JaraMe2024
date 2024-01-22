package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.Comment;
import siliconDream.jaraMe.dto.MissionCommentDTO;
import siliconDream.jaraMe.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
        private final CommentRepository commentRepository;

        public CommentServiceImpl(CommentRepository commentRepository){
            this.commentRepository = commentRepository;
        }
    public void addComment(MissionCommentDTO missionCommentDTO){
        Comment comment = new Comment();


        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId){
        commentRepository.deleteCommentByCommentIdAndUser_UserId(commentId, userId);
    }
}
