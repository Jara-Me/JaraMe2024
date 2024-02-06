package siliconDream.jaraMe.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import siliconDream.jaraMe.domain.ToDoList;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.exception.UserNotFoundException;
import siliconDream.jaraMe.repository.ToDoListRepository;
import siliconDream.jaraMe.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ToDoList createTesk(Long userId, String teskName) {
        User user;
        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found for ID: " + userId));
        } catch (UserNotFoundException e) {
            // 사용자 정의 예외 또는 Spring의 ResponseStatusException 사용
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

        try {
            ToDoList toDoList = new ToDoList(); // 기본 생성자를 사용
            toDoList.setTodayDate(LocalDate.now()); // 오늘 날짜 설정
            toDoList.setTeskName(teskName); // 할 일 이름 설정, 메소드 이름 오타 수정
            toDoList.setTeskStatus(false); // 초기 상태는 미완료로 설정, 메소드 이름 오타 수정
            toDoList.setUser(user); // 할 일을 생성한 사용자 설정
            return toDoListRepository.save(toDoList);
        } catch (DataAccessException e) {
            // 데이터베이스 액세스 중 발생하는 예외 처리
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database access error occurred", e);
        }
    }

    @Override
    @Transactional
    public void deleteTesk(Long todoListId) {
        toDoListRepository.deleteById(todoListId);
    }

    @Override
    public void toggleTeskStatus(Long todoListId) {
        ToDoList toDoList = toDoListRepository.findById(todoListId)
                .orElseThrow(() -> new RuntimeException("Tesk not found"));
        toDoList.setTeskStatus(!toDoList.isTeskStatus()); // 현재 상태를 반전시킴
        toDoListRepository.save(toDoList);
    }
    @Override
    public boolean hasIncompleteTesks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 현재 날짜에 해당하는 미완료 투두리스트 항목을 찾기.
        List<ToDoList> incompleteTesks = toDoListRepository.findByUserAndTeskStatusAndTodayDate(user, false, LocalDate.now());
        return !incompleteTesks.isEmpty(); // 미완료 항목이 있다면 true, 아니면 false 반환
    }
}
