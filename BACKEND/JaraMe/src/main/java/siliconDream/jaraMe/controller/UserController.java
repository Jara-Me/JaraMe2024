package siliconDream.jaraMe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.UserDto;
import siliconDream.jaraMe.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입을 위한 엔드포인트
    @PostMapping("signup")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        // 비밀번호 확인 유효성 검사
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("비밀번호 확인이 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우, 잘못된 요청 응답을 반환
            return ResponseEntity.badRequest().body("유효성 검사 오류");
        } else {
            boolean isSuccess = userService.create(userDto);

            if (!isSuccess) {
                // 사용자 생성에 문제가 있는 경우, 잘못된 요청 응답을 반환
                return ResponseEntity.badRequest().body("사용자 생성에 실패했습니다.");
            } else {
                // 사용자 생성이 성공한 경우, 성공 응답을 반환
                return ResponseEntity.ok("사용자가 성공적으로 생성되었습니다.");
            }
        }
    }

    // 중복 이메일 확인을 위한 엔드포인트
    @GetMapping("emailCheck")
    public ResponseEntity<Boolean> emailCheck(@RequestParam String email) {
        // 중복된 이메일 값이 있는지 확인
        String check = userService.emailCheck(email);
        return ResponseEntity.ok(check != null);
    }


    // 로그인 페이지 이동
    @RequestMapping("login")
    public String loginPage() {
        return "user/login";
    }

    // 로그인 처리
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        User user = userService.findUserByEmail(email);

        if (user == null) {
            mav.addObject("loginError", "회원가입을 해주세요."); // 이메일이 존재하지 않음
            mav.setViewName("login");
        } else if (!user.getPassword().equals(password)) {
            mav.addObject("loginError", "이메일 또는 비밀번호를 다시 확인해주세요."); // 비밀번호 불일치
            mav.setViewName("login");
        } else {
            // 로그인 성공 처리
            request.getSession().setAttribute("loggedInUser", user);
            mav.setViewName("redirect:/growth-mong"); // '성장몽 페이지'로 리디렉션
        }
        return mav;
    }


    // 로그아웃 처리
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                // 필요한 사용자 상태 업데이트
                loggedInUser.setCheckIn(false); // 사용자 상태 업데이트
                userService.saveUser(loggedInUser); // 데이터베이스에 저장
            }
            session.invalidate();
        }
        return "redirect:/login";
    }

}
