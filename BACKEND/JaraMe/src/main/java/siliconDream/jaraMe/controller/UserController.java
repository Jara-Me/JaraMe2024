package siliconDream.jaraMe.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import siliconDream.jaraMe.dto.UserDto;
import siliconDream.jaraMe.service.UserService;

@Controller
@RequestMapping("/user/**")
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
}