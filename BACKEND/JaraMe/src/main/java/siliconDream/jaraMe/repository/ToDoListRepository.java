package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import siliconDream.jaraMe.domain.ToDoList;
import siliconDream.jaraMe.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    @Query("SELECT t FROM ToDoList t WHERE t.user = :user AND t.teskStatus = :teskStatus AND t.todayDate = :todayDate")
    List<ToDoList> findByUserAndTeskStatusAndTodayDate(User user, boolean teskStatus, LocalDate todayDate);
}
