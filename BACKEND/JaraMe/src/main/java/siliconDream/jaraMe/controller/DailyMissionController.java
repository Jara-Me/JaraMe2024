package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.service.DailyMissionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dailyMission")
public class DailyMissionController {
    private final DailyMissionService dailyMissionService;


    @Autowired
    public DailyMissionController(DailyMissionService dailyMissionService) {
        this.dailyMissionService = dailyMissionService;
    }

    //오늘의 미션 조회 => 테스트완료 / 예외처리 전
    @GetMapping("/get")
    public List<DailyMissionDTO> getDailyMission(@RequestParam Long userId){
        List<DailyMissionDTO> dailyMissionDTOList = dailyMissionService.getDailyMission(userId, LocalDate.now());
        return dailyMissionDTOList;
    }
}