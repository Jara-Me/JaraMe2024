package siliconDream.jaraMe.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import siliconDream.jaraMe.domain.Group;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.service.GroupService;
import siliconDream.jaraMe.service.JoinUsersService;
import siliconDream.jaraMe.service.MissionService;
import siliconDream.jaraMe.service.PointService;

import java.util.List;

@Component
public class MissionFinishScheduler {

    private final GroupService groupService;
    private final MissionService missionService;
    private final PointService pointService;
    private final JoinUsersService joinUsersService;


    @Autowired
    public MissionFinishScheduler(GroupService groupService,
                                  MissionService missionService,
                                  PointService pointService,
                                  JoinUsersService joinUsersService) {
        this.groupService = groupService;
        this.missionService = missionService;
        this.pointService = pointService;
        this.joinUsersService = joinUsersService;
    }


    //미션 종료일 11:59:59이 되면
    //미션 인증일의 참여율이 얼마나 되는지 확인=> 포인트 서비스를 통해 포인트 적립


    @Scheduled(cron = "59 59 23 * * *") //미션 종료일의 11:59:59에 실행되도록 함.
    public String missionComplete() {

        //'오늘' 미션이 종료되는 그룹들을 리스트로 얻은 다음,
        List<Group> groups = groupService.findEndDateToday();
        for (Group group : groups) {

            Long groupId = group.getGroupId();
            //groupId로 JoinUsers테이블에 필터링해서 참여중인 유저 알아내기
            List<Long> userIds = joinUsersService.findUserIdsByGroupId(groupId);
            for (Long userId : userIds) {
                //미션에 참여한 유저들의 참여율 알아내기

                int codeNum = missionService.missionParticipationRate(userId);
                // 1/3 미만 : 미적립
                // 1/3 이상~ 2/3 미만 : 10
                // 2/3 이상~ 전체 미만 : 20
                // 전체 : 50

                //포인트 지급 로직 이용
                int updatedPoint = pointService.pointPlus(userId, codeNum);
            }

        //return 메시지 작성 => mission연장 여부에 따라 결정
            //oo미션이 종료되었습니다. 포인트 ㅇㅇ이 적립되었습니다.
        }
    return "수정 예정";
    }


}
