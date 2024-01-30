package siliconDream.jaraMe.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*커밋 전 취소

import org.springframework.security.core.Authentication;
 */
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.service.MissionPostService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/missionPost")
public class MissionPostController {
    private final MissionPostService missionPostService;

    @Autowired
    public MissionPostController(MissionPostService missionPostService) {
        this.missionPostService = missionPostService;
    }

    //미션 인증글 등록 =>테스트 완료 / 예외처리 전
    //TODO: 오늘 해당 유저가 해당 자라어스에 대한 미션인증글을 이미 작성했다면
    @PostMapping("/post")
    public ResponseEntity<String> missionPost(@RequestBody MissionPostDTO missionPostDTO,@SessionAttribute(name="userId", required=true) Long userId) {
        boolean result = missionPostService.missionPost(missionPostDTO, userId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("미션 인증글이 등록되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("미션 인증글 등록에 실패했습니다.");
        }

    }
/*테스트 목적 컨트롤러
    @PutMapping("/update") //=> 테스트 완료 / 예외처리 전
    //오늘의 미션 상태 업데이트 및 오늘의 미션 완료 시 포인트 지급 =>TODO: missionPostService말고 다른 곳으로 옮기기.  / Controller는 따로 없어도?
    public String dailyMissionUpdate(@SessionAttribute(name="userId", required=true) Long userId Long userId, @RequestParam Long missionPostId, @RequestParam Long jaraUsId) {
        //        void  dailyMissionFinish(Long userId, Long jaraUsId, MissionPost savedMissionPost, LocalDateTime postedDateTime);

        String returnMassage = missionPostService.dailyMissionUpdate(userId, jaraUsId, missionPostId);

        return returnMassage;
    }
*/

    //미션 인증글 조회 =>테스트 완료 / 예외처리 전
    @GetMapping("/get")
    public GetMissionPostDTO getMissionPost(@RequestParam Long missionPostId,@SessionAttribute(name="userId", required=true) Long userId) { //전체 인증글 목록에서 얻어내기
        GetMissionPostDTO getMissionPostDTO = missionPostService.getMissionPostDetails(missionPostId, userId);
        return getMissionPostDTO;
    }


    //미션 인증글 수정 => 테스트완료, 예외처리 전
    //TODO: 오늘=> 내용과 공개/익명 정보 모두 수정 가능
    //TODO: 오늘이 아닌 경우 => 공개/익명 정보만 수정 가능
    @PostMapping("/update")
    public ResponseEntity<String> updateMissionPost(@RequestParam Long missionPostId, @RequestBody MissionPostDTO missionPostDTO,@SessionAttribute(name="userId", required=true) Long userId) {

        String resultMessage = missionPostService.updateMissionPost(missionPostId, missionPostDTO, userId, LocalDate.now());

        //수행결과에 따라 (수정되거나 수정되지않은) 미션 인증글 조회 결과 반환
        if (resultMessage.equals( "미션 인증글이 수정되었습니다.")) {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
        }


    }
/*
    //미션 인증글 삭제
    // 미션 진행 중일 때만 삭제 가능, 미션 종료되고는 삭제 불가능
    @DeleteMapping("/delete")
    public String deleteMissionPost(@RequestParam Long missionPostId, @SessionAttribute(name = "userId" ){
        return missionPostService.deleteMissionPost(missionPostId, userId);

    }
*/


}
