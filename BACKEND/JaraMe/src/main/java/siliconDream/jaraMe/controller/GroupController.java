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
import siliconDream.jaraMe.domain.Group;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.GroupDTO;
import siliconDream.jaraMe.service.GroupService;
import siliconDream.jaraMe.service.UserService;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;

    @InitBinder("groupDTO")
    public void groupDTOInitBinder(WebDataBinder webDataBinder) {
    }

    @GetMapping("/new-group")
    public String newGroupForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User participant = userService.findUserByUsername(username);

        model.addAttribute("participant", participant);
        model.addAttribute("groupDTO", new GroupDTO());
        return "group/form";
    }

    @PostMapping("/new-group")
    public String newGroupSubmit(@Valid GroupDTO groupDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "group/form";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User participant = userService.findUserByUsername(username);

        Group newGroup = groupService.createNewGroup(groupDTO, String.valueOf(participant));
        return "redirect:/group/" + newGroup.getGroupId();
    }
}