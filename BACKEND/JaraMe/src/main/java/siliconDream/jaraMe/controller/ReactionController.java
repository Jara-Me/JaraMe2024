package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siliconDream.jaraMe.service.ReactionService;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }


    //리액션 추가
    @PostMapping("/add")
    public void addReaction(Long missionPostId,String reactionType, Long userId){
        //예외처리 : 해당 미션인증글에 리액션 추가한 적 없는지 확인
        reactionService.addReaction(missionPostId, reactionType, userId);

    }

    //리액션 삭제
    @DeleteMapping("/delete")
    public void deleteReaction(Long missionPostId,String reactionType, Long userId){
        //예외처리 : 해당 타입의 리액션 달았던 게 맞는지 확인
        reactionService.deleteReaction(missionPostId, reactionType, userId);
    }
}
