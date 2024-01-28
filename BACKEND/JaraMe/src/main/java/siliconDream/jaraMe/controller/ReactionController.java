package siliconDream.jaraMe.controller;

<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> develop_hm
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.MissionReactionDTO;
import siliconDream.jaraMe.service.ReactionService;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }


    //리액션 추가
    @PostMapping("/add")
<<<<<<< HEAD
    public String addReaction(@RequestBody MissionReactionDTO missionReactionDTO, @RequestParam Long userId){
        //예외처리 : 해당 미션인증글에 리액션 추가한 적 없는지 확인
        return reactionService.addReaction(missionReactionDTO, userId);

=======
    public ResponseEntity<String> addReaction(@RequestBody MissionReactionDTO missionReactionDTO, @SessionAttribute(name = "userId", required = true) Long userId) {
        //예외처리 : 해당 미션인증글에 리액션 추가한 적 없는지 확인
        return reactionService.addReaction(missionReactionDTO, userId);
>>>>>>> develop_hm
    }

    //리액션 삭제
    @DeleteMapping("/delete")
<<<<<<< HEAD
    public String deleteReaction(@RequestBody MissionReactionDTO missionReactionDTO, @RequestParam Long userId){
        //예외처리 : 해당 타입의 리액션 달았던 게 맞는지 확인
        return reactionService.deleteReaction(missionReactionDTO, userId);
=======
    public ResponseEntity<String> deleteReaction(@RequestBody MissionReactionDTO missionReactionDTO, @SessionAttribute(name = "userId", required = true) Long userId) {
        //예외처리 : 해당 타입의 리액션 달았던 게 맞는지 확인

        String resultMessage = reactionService.deleteReaction(missionReactionDTO, userId);
        if (resultMessage.equals("리액션이 취소되었습니다.")) {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
        }
>>>>>>> develop_hm
    }
}
