package siliconDream.jaraMe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;
import siliconDream.jaraMe.service.JaraUsService;
import siliconDream.jaraMe.service.UserService;

@Controller
@RequiredArgsConstructor
public class JaraUsController {

    private final JaraUsService jaraUsService;
    private final UserService userService;

    @InitBinder("jaraUsDTO")
    public void jaraUsDTOInitBinder(WebDataBinder webDataBinder) {
    }

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
/*주석처리 => createNewJaraUs 주석처리해놔서 여기서 인식못해서 에러남.

    @PostMapping("/new-jaraUs")
    public String newJaraUsSubmit(@Valid JaraUsDTO jaraUsDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "jaraUs/form";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //수정한 부분
        String userIdString = authentication.getName();
        Long userId = Long.parseLong(userIdString);
        User participant = userService.findUserByUserId(userId);

        JaraUs newJaraUs = jaraUsService.createNewJaraUs(jaraUsDTO,userIdString);
        //수정 전 : JaraUs newJaraUs = jaraUsService.createNewJaraUs(jaraUsDTO, String.valueOf(participant));
        return "redirect:/jaraUs/" + newJaraUs.getJaraUsId();
    }*/
}