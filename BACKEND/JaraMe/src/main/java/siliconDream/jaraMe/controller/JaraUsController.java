package siliconDream.jaraMe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.service.JaraUsService;
import siliconDream.jaraMe.service.UserService;

import java.util.List;

@Controller
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
    @PostMapping("create-jaraUs")
    public ResponseEntity<?> createNewJaraUs(@ModelAttribute JaraUsDTO jaraUsDTO, HttpServletRequest request) {
        Long currentUserId = getCurrentUserIdFromRequest(request);

        if (currentUserId == null) {
            // 사용자 ID를 확인할 수 없는 경우 처리
            // (예: 인증되지 않은 사용자의 경우 오류 응답 반환)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증되지 않음");
        }

        try {
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

     // 검색 기능 추가
    @GetMapping("/search")
    public ResponseEntity<List<JaraUsDTO>> searchJaraUs(@RequestParam String keyword) {
        List<JaraUsDTO> searchResults = jaraUsService.searchJaraUs(keyword);
        return ResponseEntity.ok(searchResults);
    }
 }