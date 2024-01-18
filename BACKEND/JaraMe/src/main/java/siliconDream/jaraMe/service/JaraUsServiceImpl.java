package siliconDream.jaraMe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.service.JaraUsService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JaraUsServiceImpl implements JaraUsService {

    private final JaraUsRepository jaraUsRepository;
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

    public List<JaraUs> findEndDateToday(){
        return jaraUsRepository.findEndDateToday(LocalDate.now());
    }




}