package siliconDream.jaraMe.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.repository.ScheduleRepository;
import siliconDream.jaraMe.service.JaraUsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class JaraUsServiceImpl implements JaraUsService {

    private final JaraUsRepository jaraUsRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Override
    public JaraUs createNewJaraUs(JaraUsDTO jaraUsDTO, String userId) {
        User administrator = userService.findUserByUserId(jaraUsDTO.getAdminUserId());
        JaraUs jaraUs = new JaraUs();
        jaraUs.setJaraUsName(jaraUsDTO.getJaraUsName());
        jaraUs.setMissionName(jaraUsDTO.getMissionName());
        jaraUs.setAdminUserId(administrator);
        jaraUs.setRecurrence(jaraUsDTO.getRecurrence());
        LocalDate startDate = jaraUsDTO.getStartDate();
        if (startDate != null && startDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("시작일은 적어도 내일 이후여야 합니다.");
        }

        return jaraUsRepository.save(jaraUs);
    }


    @Override
    public void participateInJaraUs(Long jaraUsId, String userId) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));
    }

    @Override
    public void runJaraUs(Long jaraUsId, String userId) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        // Perform run logic...
    }

    @Override
    public void editJaraUs(Long jaraUsId, JaraUsDTO jaraUsDTO) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        // Perform edit logic...
        jaraUs.setJaraUsName(jaraUsDTO.getJaraUsName());
        jaraUs.setMissionName(jaraUsDTO.getMissionName());
        // Set other fields...

        jaraUsRepository.save(jaraUs);
    }

    @Override
    public void deleteJaraUs(Long jaraUsId) {
        // Implementation for delete logic...
        jaraUsRepository.deleteById(jaraUsId);
    }

    @Override
    public List<JaraUs> findJaraUsByAdministrator(Long adminUserId) {
        return null;
    }


    @Override
    public List<JaraUs> findExpiredJaraUs() {
        // Implementation for finding expired JaraUs instances...
        return jaraUsRepository.findExpiredJaraUs(LocalDate.now());
    }
    // Other method implementations...

/* //주석처리돼있던 것같은 부분 (커밋 기록 기반)
        @Override
        public JaraUsDTO createNewJaraUs(JaraUsDTO jaraUsDTO, String userId) {
            // Extract relevant information from jaraUsDTO
            String jaraUsName = jaraUsDTO.getJaraUsName();
            String missionName = jaraUsDTO.getMissionName();
            String explanation = jaraUsDTO.getExplanation();
            String rule = jaraUsDTO.getRule();
            String jaraUsProfileImage = jaraUsDTO.getJaraUsProfileImage();
            int maxMember = jaraUsDTO.getMaxMember();
            boolean display = jaraUsDTO.isDisplay();
            LocalDate startDate = jaraUsDTO.getStartDate();
            LocalDate endDate = jaraUsDTO.getEndDate();
            //Set<Recurrence> recurrence = jaraUsDTO.getRecurrence(); 추가예정
    
    

            // Create a new jaraUs
            JaraUs jaraUs = JaraUs.createNewJaraUs(jaraUsName, missionName, explanation, rule, jaraUsProfileImage,
                    maxMember, display, startDate, endDate); //, recurrence 추가예정
    
            // Save the jaraUs
            JaraUs savedJaraUs = jaraUsRepository.save(jaraUs);
    
            // Convert the savedJaraUs to JaraUsDTO and return
            return convertToDTO(savedJaraUs);
        }
    
        // Your logic to convert JaraUs to JaraUsDTO
        private JaraUsDTO convertToDTO(JaraUs jaraUs) {
            return new JaraUsDTO(
                    jaraUs.getJaraUsId(),
                    jaraUs.getJaraUsName(),
                    jaraUs.getMissionName(),
                    jaraUs.getExplanation(),
                    jaraUs.getRule(),
                    jaraUs.getJaraUsProfileImage(),
                    jaraUs.getMaxMember(),
                    jaraUs.isDisplay(),
                    jaraUs.getStartDate(),
                    jaraUs.getEndDate()

            );  // jaraUs.getRecurrence() 추가 예정
    
        }*/

    //미션완주일이 오늘인 그룹 찾아내기
    public List<JaraUs> findEndDateToday() {
        return jaraUsRepository.findEndDateToday(LocalDate.now());


    }





}
