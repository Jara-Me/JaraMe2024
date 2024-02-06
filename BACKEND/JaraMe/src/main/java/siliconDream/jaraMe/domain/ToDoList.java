package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoListId;

    @Column(name = "today_date")
    private LocalDate todayDate;
    @Column(name = "tesk_name")
    private String teskName;
    @Column(name = "tesk_status")
    private boolean teskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;



    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    public void setTeskName(String teskName) {
        this.teskName = teskName;
    }

    public void setTeskStatus(boolean teskStatus) {
        this.teskStatus = teskStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 새로운 getter 메소드
    public boolean isTeskStatus() {
        return teskStatus;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }
    public String getTeskName() {
        return teskName;
    }

    public User getUser() {
        return user;
    }
}

