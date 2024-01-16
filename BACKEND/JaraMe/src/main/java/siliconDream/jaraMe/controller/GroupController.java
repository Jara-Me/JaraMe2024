package siliconDream.jaraMe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import siliconDream.jaraMe.domain.Group;
import siliconDream.jaraMe.service.GroupService;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupFormValidator groupFormValidator;

    @InitBinder("groupForm")
    public void groupFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(groupFormValidator);
    }

    @GetMapping("/new-group")
    public String newGroupForm(@CurrentParticipant Participant participant, Model model) {
        model.addAttribute(participant);
        model.addAttribute(new GroupForm());
        return "group/form";
    }

    @PostMapping("/new-group")
    public String newGroupSubmit(@CurrentParticipant Participant participant, @Valid GroupForm groupForm, Errors errors) {
        if (errors.hasErrors()) {
            return "group/form";
        }
        Group newGroup = groupService.createNewGroup(groupForm, participant);
        return "redirect:/group/" + URLEncoder.encode(newGroup.getPath(), StandardCharsets.UTF_8);
    }
}