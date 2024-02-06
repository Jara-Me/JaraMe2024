package siliconDream.jaraMe.dto;

import siliconDream.jaraMe.domain.ToDoList;

import java.time.LocalDate;

public class ToDoListDto {
    private Long todoListId;
    private LocalDate todayDate;
    private String teskName;
    private boolean teskStatus;
    private Long userId;

    // 생성자, getter, setter
    public ToDoListDto(ToDoList toDoList) {
        this.todoListId = toDoList.getTodoListId();
        this.todayDate = toDoList.getTodayDate();
        this.teskName = toDoList.getTeskName();
        this.teskStatus = toDoList.isTeskStatus();
        this.userId = toDoList.getUser().getUserId(); // User 객체에서 userId만 추출
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public String getTeskName() {
        return teskName;
    }

    public void setTeskName(String teskName) {
        this.teskName = teskName;
    }

    public boolean isTeskStatus() {
        return teskStatus;
    }

    public void setTeskStatus(boolean teskStatus) {
        this.teskStatus = teskStatus;
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

