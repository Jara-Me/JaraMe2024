package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.Notification;
import siliconDream.jaraMe.dto.NotificationDTO;
import siliconDream.jaraMe.repository.NotificationRepository;
import siliconDream.jaraMe.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createNotification(Long userId, String message) {
        // User 엔티티를 userId를 사용해 조회
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification();
        notification.setUser(user); // 조회한 User 객체를 사용
        notification.setMessage(message);
        notification.setCreatedDateTime(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

   //사용자가 읽지 않은 알림 목록 조회
    @Override
    public List<NotificationDTO> getUserNotifications(Long userId) {
        // User 객체를 사용하여 findByUserAndIsRead 메소드 호출
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Notification> notifications = notificationRepository.findByUserAndIsRead(user,false);
        return notifications
                .stream()
                .map(notification -> new NotificationDTO(
                        notification.getId(),
                        user.getUserId(), // 직접 userId 사용
                        notification.getMessage(),
                        notification.getCreatedDateTime(),
                        notification.isRead())
                )
                .collect(Collectors.toList());
    }
    
     //특정 알림을 읽음 상태로 업데이트
    @Override
    public boolean markAsRead(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            Notification n = notification.get();
            n.setRead(true);
            notificationRepository.save(n);
            return true;
        }
        return false;
    }

    //사용자의 모든 알림 목록을 최신순으로 조회
    @Override
    public List<NotificationDTO> getAllNotificationsForUser(Long userId) {
        // User 객체를 사용하여 findByUserId 메소드 호출
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return notificationRepository.findByUserId(userId).stream()
                .sorted(Comparator.comparing(Notification::getCreatedDateTime).reversed())
                .map(n -> new NotificationDTO(n.getId(), user.getUserId(), n.getMessage(), n.getCreatedDateTime(), n.isRead()))
                .collect(Collectors.toList());
    }
}
