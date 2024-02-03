package siliconDream.jaraMe.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.JoinUsers;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.service.JaraUsService;
import siliconDream.jaraMe.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/jaraus")
@RequiredArgsConstructor
public class JaraUsController {

    @Autowired
    private final JaraUsService jaraUsService;
    private final UserService userService;

    @InitBinder("jaraUsDTO")
    public void jaraUsDTOInitBinder(WebDataBinder webDataBinder) {
    }

/*
    @GetMapping("/new-jaraUs")
    public String newJaraUsForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //수정한 부분
        String userIdString = authentication.getName();
        Long userId = Long.parseLong(userIdString);
        User participant = userService.findUserByUserId(userId);

        model.addAttribute("participant", participant);
        model.addAttribute("jaraUsDTO", new JaraUsDTO());
        return "jaraUs/form";
    }
*/

    @PostMapping("/create")
    public ResponseEntity<?> createNewJaraUs(@RequestBody @Valid JaraUsDTO jaraUsDTO, HttpServletRequest request) {
        Long currentUserId = getCurrentUserIdFromRequest(request);

        if (currentUserId == null) {
            // 사용자 ID를 확인할 수 없는 경우 처리
            // (예: 인증되지 않은 사용자의 경우 오류 응답 반환)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증되지 않음");
        }

        try {
            if (jaraUsDTO.getRecurrence() == null || jaraUsDTO.getRecurrence().isEmpty()) {
                throw new IllegalArgumentException("반복 주기 설정 필요");
            }
            jaraUsDTO.setJaraUsProfileImage("your_image_url_or_base64_data");
            JaraUs createdNewJaraUs = jaraUsService.createNewJaraUs(jaraUsDTO, currentUserId);

            Long createdJaraUsId = createdNewJaraUs.getJaraUsId();

            // 생성된 JaraUs ID와 함께 성공 응답 반환
            return ResponseEntity.ok("JaraUs가 생성되었습니다. ID: " + createdJaraUsId);
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 입력 예외 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Long getCurrentUserIdFromRequest(HttpServletRequest request) {
        // 세션에서 사용자 정보 추출 또는 다른 사용자 지정 메커니즘에서 가져올 수 있음
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 예: 세션에 사용자 정보가 저장되어 있다고 가정
            User user = (User) session.getAttribute("user");

            if (user != null) {
                return user.getUserId(); // 실제 User 엔터티에 기반하여 조정
            }
        }

        return null; // 사용자 정보를 사용할 수 없는 경우 null 반환
    }

    @PostMapping("/participate")
    public ResponseEntity<?> participateInJaraUs(@RequestBody @Valid JaraUsDTO jaraUsDTO, HttpServletRequest request) {
        Long currentUserId = getCurrentUserIdFromRequest(request);

        if (currentUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증되지 않음");
        }

        try {
            jaraUsService.participateInJaraUs(jaraUsDTO, currentUserId);

            // Return success response
            return ResponseEntity.ok("자라어스 가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw/{jaraUsId}/{userId}")
    public ResponseEntity<?> withdrawFromJaraUs(
            @PathVariable Long jaraUsId,
            @PathVariable Long userId
    ) {
        try {
            jaraUsService.withdrawFromJaraUs(jaraUsId, userId);
            return ResponseEntity.ok("자라어스에서 탈퇴 성공");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("자라어스를 찾을 수 없음");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/edit-admin/{jaraUsId}/{adminUserId}")
    public ResponseEntity<?> editJaraUsByAdmin(
            @PathVariable Long jaraUsId,
            @PathVariable Long adminUserId,
            @RequestBody JaraUsDTO jaraUsDTO
    ) {
        try {
            jaraUsService.editJaraUsByAdmin(jaraUsId, adminUserId, jaraUsDTO);
            return ResponseEntity.ok("관리자에 의해 자라어스 수정 성공");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("자라어스를 찾을 수 없음");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/expired")
    public ResponseEntity<List<JaraUs>> findExpiredJaraUs() {
        List<JaraUs> expiredJaraUs = jaraUsService.findExpiredJaraUs();
        return ResponseEntity.ok(expiredJaraUs);
    }

    @PostMapping("/edit-information/{userId}")
    public ResponseEntity<?> editJaraUsInformation(
            @PathVariable Long userId,
            @RequestBody JaraUsDTO jaraUsDTO
    ) {
        try {
            JaraUs editedJaraUs = jaraUsService.editJaraUsInformation(userId, jaraUsDTO);
            return ResponseEntity.ok("자라어스 정보 수정 성공");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("자라어스를 찾을 수 없음");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/information/{jaraUsId}")
    public ResponseEntity<?> getJaraUsInformation(@PathVariable Long jaraUsId) {
        try {
            JaraUs jaraUsInfo = jaraUsService.findByjaraUsId(jaraUsId);
            JaraUsDTO jaraUsDTO = jaraUsService.convertToDTO(jaraUsInfo);
            return ResponseEntity.ok(jaraUsDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("JaraUs를 찾을 수 없음");
        }
    }

    @GetMapping("/my-groups/{userId}")
    public ResponseEntity<?> getMyGroups(@PathVariable Long userId) {
        try {
            List<JaraUsDTO> myGroups = jaraUsService.getJaraUsListForUser(userId);
            return ResponseEntity.ok(myGroups);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 그룹을 찾을 수 없음");
        }
    }

        // 검색 기능 추가
    @GetMapping("/search")
    public ResponseEntity<List<JaraUsDTO>> searchJaraUs(@RequestParam String keyword) {
        List<JaraUsDTO> searchResults = jaraUsService.searchJaraUs(keyword);
        return ResponseEntity.ok(searchResults);
    }
 }