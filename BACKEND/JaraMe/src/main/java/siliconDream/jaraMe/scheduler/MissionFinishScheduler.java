package siliconDream.jaraMe.scheduler;

import org.springframework.stereotype.Component;

@Component
public class MissionFinishScheduler {
/*
    private final JaraUsService jaraUsService;
    private final MissionPostService missionService;
    private final PointService pointService;
    private final JoinUsersService joinUsersService;


    @Autowired
    public MissionFinishScheduler(JaraUsService jaraUsService,
                                  MissionPostService missionService,
                                  PointService pointService,
                                  JoinUsersService joinUsersService) {
        this.jaraUsService = jaraUsService;
        this.missionService = missionService;
        this.pointService = pointService;
        this.joinUsersService = joinUsersService;
    }


    //미션 종료일 11:59:59이 되면
    //미션 인증일의 참여율이 얼마나 되는지 확인=> 포인트 서비스를 통해 포인트 적립


    @Scheduled(cron = "59 59 23 * * *") //미션 종료일의 11:59:59에 실행되도록 함.
    public String missionComplete() {

        //'오늘' 미션이 종료되는 그룹들을 리스트로 얻은 다음,
        List<JaraUs> jaraUses = jaraUsService.findEndDateToday();

        for (JaraUs jaraUs : jaraUses) {

            Long jaraUsId = jaraUs.getJaraUsId();
            //jaraUsId로 JoinUsers테이블에 필터링해서 참여중인 유저 알아내기
            List<Long> userIds = joinUsersService.findUserIdsByJaraUsId(jaraUsId);
            for (Long userId : userIds) {



                //미션에 참여한 유저들의 참여율 알아내기
                int plusPoint = missionService.missionParticipationRate(userId);
                // 1/3 미만 : 미적립
                // 1/3 이상~ 2/3 미만 : 10
                // 2/3 이상~ 전체 미만 : 20
                // 전체 : 50

                //포인트 지급 로직 이용
                int updatedPoint = pointService.pointPlus(userId, plusPoint);
            }

        //return 메시지 작성 => mission연장 여부에 따라 결정
            //oo미션이 종료되었습니다. 포인트 ㅇㅇ이 적립되었습니다.
        }
    return "수정 예정";
    }

*/
}

/*스케줄링
* 그룹이 생성될 때 실행(ScheduleService실행)
* 기간을 정해서, 그 기간 안에 요일을 통해 특정 날짜를 얻어내면, Schedule테이블과 ScheduleDate테이블에 값을 채워서 save하는 구조
*
* 미션에 참여한 유저의 참여율 알아내기
* Schedule테이블에서 미션(과 유저)로 날짜를 찾은 뒤, for문을 둘면서 해당하는 날짜들에 인증을 했는지 여부를 가려내서 퍼센테이지 계산
*  */

