package siliconDream.jaraMe.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> missionPost(@RequestBody MissionPostDTO missionPostDTO, @RequestParam Long userId) {
        String returnMessage = missionPostService.missionPost(missionPostDTO,userId);

        return ResponseEntity.ok(returnMessage);
    }

    @PutMapping("/update") //=> 테스트 완료 / 예외처리 전
    //오늘의 미션 상태 업데이트 및 오늘의 미션 완료 시 포인트 지급 =>TODO: missionPostService말고 다른 곳으로 옮기기.  / Controller는 따로 없어도?
    public String dailyMissionUpdate(@RequestParam Long userId, @RequestParam Long  missionPostId, @RequestParam Long jaraUsId ){
        //        void  dailyMissionFinish(Long userId, Long jaraUsId, MissionPost savedMissionPost, LocalDateTime postedDateTime);

        String returnMassage = missionPostService.dailyMissionUpdate(userId, jaraUsId, missionPostId);

                return returnMassage;
    }


    //미션 인증글 조회 =>테스트 완료 / 예외처리 전
    @GetMapping("/get")
    public GetMissionPostDTO getMissionPost(@RequestParam Long missionPostId,@RequestParam Long userId){ //전체 인증글 목록에서 얻어내기
        GetMissionPostDTO getMissionPostDTO = missionPostService.getMissionPostDetails(missionPostId,userId);
        return getMissionPostDTO;
    }





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    //미션 인증글 수정 => 테스트완료, 예외처리 전
    //TODO: 오늘=> 내용과 공개/익명 정보 모두 수정 가능
    //TODO: 오늘이 아닌 경우 => 공개/익명 정보만 수정 가능
    @PostMapping("/update")
    public String updateMissionPost(@RequestParam Long missionPostId, @RequestBody MissionPostDTO missionPostDTO, @RequestParam Long userId){

        String resultMessage = missionPostService.updateMissionPost(missionPostId,missionPostDTO, userId, LocalDate.now());

        //수행결과에 따라 (수정되거나 수정되지않은) 미션 인증글 조회 결과 반환
        if (resultMessage=="수정되었습니다.") {
         //   GetMissionPostDTO getMissionPostDTO = missionPostService.getMissionPostDetails(missionPostId); //TODO: 반환값 메시지 담기게 수정예정!!
            return resultMessage;
        }else { //수행결과로 받은 반환값인 resultMessage 들어가도록 수정하기
         //   GetMissionPostDTO getMissionPostDTO = missionPostService.getMissionPostDetails(missionPostId); //수정 예정
            return resultMessage;
             }


    }

    //미션 인증글 삭제
    //TODO: 미션 진행 중일 때만 삭제 가능, 미션 종료되고는 삭제 불가능
    @DeleteMapping("/delete")
    public String deleteMissionPost(@RequestParam Long missionPostId, @RequestParam Long userId ){
        return missionPostService.deleteMissionPost(missionPostId, userId);

    }




}
