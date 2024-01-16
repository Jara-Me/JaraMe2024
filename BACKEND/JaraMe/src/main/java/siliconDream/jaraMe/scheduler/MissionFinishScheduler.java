/*package siliconDream.jaraMe.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import siliconDream.jaraMe.domain.Group;

import java.util.List;

@Component
public class MissionFinishScheduler {

    private final GroupService groupService;

    @Autowired
    public MissionFinishScheduler(GroupService groupService){
        this.groupService=groupService;
    }


    //미션 종료일 11:59:59이 되면
    //미션 인증일의 참여율이 얼마나 되는지 확인=> 포인트 서비스를 통해 포인트 적립

    // 1/3 미만 : 미적립
    // 1/3 이상~ 2/3 미만 : 10
    // 2/3 이상~ 전체 미만 : 20
    // 전체 : 50
    public MissionFinishScheduler(){}

    @Scheduled(cron ="59 59 23 * * *") //미션 종료일의 11:59:59에 실행되도록 함.
    //'오늘' 미션이 종료되는 그룹들을 리스트로 얻은 다음,
    List<Group> groups = groupService.findEndDateToday();
    for (Group group : groups) {

        //groupId로 JoinUsers테이블에 필터링해서 참여중인 유저 알아내기

        //미션에 참여한 유저들의 참여율 알아내기
        groupService.missionParticipationRate(userId);
        //참여율에 따라 포인트 지급 (pointService? )
    }






}
*/