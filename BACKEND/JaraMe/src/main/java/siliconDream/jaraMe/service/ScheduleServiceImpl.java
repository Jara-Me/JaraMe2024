package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements  ScheduleService{
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository=scheduleRepository;
    }

    //미션 시작일,종료일 사이의 반복요일의 정확한 날짜 구해서 schedule테이블에 기록하기
    public void jaraUsScheduling(JaraUs jaraUs){
        Long jaraUsId = jaraUs.getJaraUsId();
        LocalDate startDate = jaraUs.getStartDate();
        LocalDate endDate = jaraUs.getEndDate();
        Set<Recurrence> recurrenceSet = jaraUs.getRecurrence();

        List<LocalDate> realDates = new ArrayList<>();
        LocalDate nowDate = startDate;
        while (nowDate.isBefore(endDate)) {
            if (recurrenceSet.contains(nowDate.getDayOfWeek())) {
                realDates.add(nowDate);
            }
            nowDate.plusDays(1);
        }
        for (LocalDate one : realDates){
            scheduleRepository.saveSchedule(jaraUsId,one);
        }

    }
}
