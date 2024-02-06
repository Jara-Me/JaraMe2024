package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.ToDoList;
import siliconDream.jaraMe.dto.ToDoListDto;
import siliconDream.jaraMe.service.ToDoListService;
@CrossOrigin(originPatterns = "http://localhost:5173")
@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    @PostMapping("/create")
    public ResponseEntity<?> createTesk(@SessionAttribute(name="userId", required=true) Long userId, @RequestParam("teskName") String teskName) {
        ToDoList toDoList = toDoListService.createTesk(userId, teskName);
        ToDoListDto response = new ToDoListDto(toDoList); // DTO를 사용하여 응답 객체 생성
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{todoListId}")
    public ResponseEntity<?> deleteTesk(@PathVariable(name= "todoListId") Long todoListId) {
        toDoListService.deleteTesk(todoListId);
        return ResponseEntity.ok().body("Tesk deleted successfully");
    }

    @PostMapping("/toggle/{todoListId}")
    public ResponseEntity<?> toggleTeskStatus(@PathVariable(name= "todoListId") Long todoListId) {
        toDoListService.toggleTeskStatus(todoListId);
        return ResponseEntity.ok().body("Tesk status toggled successfully");
    }
}
