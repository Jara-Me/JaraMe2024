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
/*오류나는 부분 -커밋
    @GetMapping("/new-jaraUs")
    public String newJaraUsForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User participant = userService.findUserByUsername(username);

        model.addAttribute("participant", participant);
        model.addAttribute("jaraUsDTO", new JaraUsDTO());
        return "jaraUs/form";
    }

    @PostMapping("/new-jaraUs")
    public String newJaraUsSubmit(@Valid JaraUsDTO jaraUsDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "jaraUs/form";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User participant = userService.findUserByUsername(username);

        JaraUs newJaraUs = jaraUsService.createNewJaraUs(jaraUsDTO, String.valueOf(participant));
        return "redirect:/jaraUs/" + newJaraUs.getJaraUsId();}*/
}