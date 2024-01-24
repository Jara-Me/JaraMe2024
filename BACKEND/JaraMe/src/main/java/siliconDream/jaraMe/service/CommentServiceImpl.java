package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.Comment;
import siliconDream.jaraMe.dto.MissionCommentDTO;
import siliconDream.jaraMe.repository.CommentRepository;
import siliconDream.jaraMe.repository.MissionPostRepository;
import siliconDream.jaraMe.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
        private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MissionPostRepository missionPostRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              MissionPostRepository missionPostRepository){
            this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.missionPostRepository = missionPostRepository;
    }
    public void addComment(Long userId, MissionCommentDTO missionCommentDTO){
        Comment comment = new Comment();
        comment.setUser(userRepository.findByUserId(userId));
        comment.setMissionPost(missionPostRepository.findByMissionPostId(missionCommentDTO.getMissionPostId()));
        comment.setCommentDate(missionCommentDTO.getCommentDateTime());
        comment.setCommentContent(missionCommentDTO.getCommentContent());
        commentRepository.save(comment);
    }

    public String deleteComment(Long commentId, Long userId){
        //미션 종료일 지난 거면 못하도록 예외처리하기
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if (comment.getUser().getUserId().equals(userId)){
            commentRepository.delete(commentRepository.findByCommentId(commentId));
            return "댓글이 삭제되었습니다.";

        }return "작성자가 일치하지않습니다.";
    }
}