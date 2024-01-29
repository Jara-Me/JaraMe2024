package siliconDream.jaraMe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//커밋 전 취소import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.UserDto;
import siliconDream.jaraMe.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // 회원가입 페이지 이동
    @RequestMapping("signup")
    public String create() {
        return "user/signup";
    }

    // signup 시 email 중복확인 버튼 구현
    @RequestMapping("emailCheck")
    @ResponseBody
    public boolean emailCheck(@RequestParam("email") String email) {
        // DB에 가서 email 중복값이 있는지 조회
        String check = userService.emailCheck(email);
        return check != null;
    }
/*커밋 전 취소
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ModelAndView createPost(@Valid UserDto userDto, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        // 기본적인 비밀번호 확인 유효성 검증
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "비밀번호 확인이 일치하지 않습니다");
        }

        if (bindingResult.hasErrors()) {
            // 유효성 검증 오류가 있을 경우 회원가입 페이지로 이동
            mav.addObject("message", "fault");
            mav.setViewName("user/signup");
        } else {
            boolean tf = userService.create(userDto);
            System.out.println(tf);

            if (!tf) {
                mav.addObject("message", "fault");
                mav.setViewName("user/signup");
            } else {
                mav.addObject("message", "success");
                mav.setViewName("main/main");
            }
        }
        return mav;
    }
    */

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
