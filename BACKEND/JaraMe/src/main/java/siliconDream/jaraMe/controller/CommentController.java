package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.MissionCommentDTO;
import siliconDream.jaraMe.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    //미션 인증글 댓글 등록
    @PostMapping("/add")
    //예외처리 : 공백만 아니면 될 것같음.
    public void addComment(@RequestBody MissionCommentDTO missionCommentDTO) {
        commentService.addComment(missionCommentDTO);
    }

    //미션 인증글 댓글 삭제
    @DeleteMapping("/delete")
    //예외처리 : 해당 미션 인증글에서 실제로 댓글 삭제되었는지 확인
    public void deleteComment(Long commentId, Long userId) {
        commentService.deleteComment(commentId, userId);
    }

    /*
    //미션 인증글 댓글 알림?=> 웹소켓?
    @GetMapping("/get")
    public CommentNoticeDTO noticeComment(Long userId){

    }*/

}

