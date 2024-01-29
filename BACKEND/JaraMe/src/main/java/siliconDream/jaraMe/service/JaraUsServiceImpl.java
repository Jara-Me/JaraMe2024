package siliconDream.jaraMe.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.JoinUsers;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.repository.ScheduleRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static siliconDream.jaraMe.service.ScheduleService.*;


@Service
@RequiredArgsConstructor
@Transactional
public class JaraUsServiceImpl implements JaraUsService {

    private final JaraUsRepository jaraUsRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final ScheduleService scheduleService;

    @Override
    public JaraUs createNewJaraUs(JaraUsDTO jaraUsDTO) {
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
        LocalDate endDate = jaraUsDTO.getEndDate();

        JaraUs savedJaraUs = jaraUsRepository.save(jaraUs); // JaraUs 레코드 저장

        // 스케줄링 작업 실행
        scheduleService.jaraUsScheduling(savedJaraUs);

        return savedJaraUs;
    }


    @Override
    public void participateInJaraUs(Long jaraUsId, Long userId) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        User participant = userService.findUserByUserId(userId);

        if (jaraUs.getJoinUsers().size() >= jaraUs.getMaxMember()) {
            throw new IllegalStateException("Maximum number of participants reached for JaraUs");
        }

        if (jaraUs.getJoinUsers().stream().anyMatch(joinUser -> joinUser.getUser().equals(participant))) {
            throw new IllegalStateException("User is already a participant in JaraUs");
        }

        JoinUsers joinUsers = new JoinUsers();
        joinUsers.setUser(participant);
        joinUsers.setJaraUs(jaraUs);
        joinUsers.setSignUpDate(LocalDate.now());

        jaraUs.getJoinUsers().add(joinUsers);

        jaraUsRepository.save(jaraUs);
    }

    @Override
    public void withdrawFromJaraUs(Long jaraUsId, Long userId) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        User withdrawingUser = userService.findUserByUserId(userId);

        if (jaraUs.getAdminUserId().equals(withdrawingUser.getUserId())) {
            throw new IllegalStateException("Administrator cannot withdraw without handing over the role.");
        }

        jaraUs.getJoinUsers().removeIf(joinUser -> joinUser.getUser().equals(withdrawingUser));

        jaraUsRepository.save(jaraUs);
    }


    @Override
    public void editJaraUsByAdmin(Long jaraUsId, Long adminUserId, JaraUsDTO jaraUsDTO) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsId)
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        User adminUser = userService.findUserByUserId(adminUserId);

        if (!jaraUs.getAdminUserId().equals(adminUser.getUserId())) {
            throw new IllegalStateException("You are not the administrator of this JaraUs.");
        }

        // Get the list of joinUsers sorted by signup date
        List<JoinUsers> sortedJoinUsers = jaraUs.getJoinUsers().stream()
                .sorted(Comparator.comparing(JoinUsers::getSignUpDate))
                .collect(Collectors.toList());

        if (!sortedJoinUsers.isEmpty()) {
            JoinUsers nextAdmin = sortedJoinUsers.get(0);
            User newAdminUser = nextAdmin.getUser();

            jaraUs.setAdminUserId(newAdminUser);
        }

        jaraUs.setJaraUsName(jaraUsDTO.getJaraUsName());
        jaraUs.setMissionName(jaraUsDTO.getMissionName());
        jaraUs.setRecurrence(jaraUsDTO.getRecurrence());
        jaraUs.setEndDate(jaraUsDTO.getEndDate());

        jaraUsRepository.save(jaraUs);
    }

    @Override
    public List<JaraUs> findExpiredJaraUs() {
        List<JaraUs> allJaraUs = jaraUsRepository.findAll();

        List<JaraUs> expiredJaraUs = allJaraUs.stream()
                .filter(jaraUs -> jaraUs.getStartDate() == null && jaraUs.getEndDate() == null)
                .collect(Collectors.toList());

        return expiredJaraUs;
    }

    @Override
    public JaraUs editJaraUsInformation(Long userId, JaraUsDTO jaraUsDTO) {
        JaraUs jaraUs = jaraUsRepository.findById(jaraUsDTO.getJaraUsId())
                .orElseThrow(() -> new EntityNotFoundException("JaraUs not found"));

        User editorUser = userService.findUserByUserId(userId);

        // Check if the editor is the administrator of the JaraUs
        if (!jaraUs.getAdminUserId().equals(editorUser)) {
            throw new IllegalStateException("Only administrators can edit JaraUs information.");
        }

        jaraUs.setJaraUsName(jaraUsDTO.getJaraUsName());
        jaraUs.setMissionName(jaraUsDTO.getMissionName());
        jaraUs.setRecurrence(jaraUsDTO.getRecurrence());



        LocalDate startDate = jaraUsDTO.getStartDate();
        if (startDate != null && startDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Start date must be at least one day ahead of today.");
        }
        jaraUs.setStartDate(startDate);

        LocalDate endDate = jaraUsDTO.getEndDate();
        jaraUs.setEndDate(endDate);

        // Save the updated JaraUs entity
        return jaraUsRepository.save(jaraUs);
    }



    //미션완주일이 어제인 그룹 찾아내기
    public List<JaraUs> findEndDateYesterDay() {
        return jaraUsRepository.findEndDateYesterDay(LocalDate.now().minusDays(1));


    }





}
