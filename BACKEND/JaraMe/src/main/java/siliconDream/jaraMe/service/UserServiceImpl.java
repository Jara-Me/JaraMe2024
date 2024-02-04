package siliconDream.jaraMe.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
/*커밋 전 취소
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
*/
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.dto.LoginResponse;
import siliconDream.jaraMe.dto.UserDto;
import siliconDream.jaraMe.dto.UserProfileInfoDTO;
import siliconDream.jaraMe.repository.JoinUsersRepository;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.repository.ScheduleRepository;
import siliconDream.jaraMe.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private JoinUsersRepository joinUsersRepository;
    private ScheduleRepository scheduleRepository;
    private JaraUsRepository jaraUsRepository;


    public UserServiceImpl(UserRepository userRepository, JoinUsersRepository joinUsersRepository,
                           JaraUsRepository jaraUsRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.joinUsersRepository = joinUsersRepository;
        this.jaraUsRepository = jaraUsRepository;
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    public boolean create(UserDto userDto) {
        if (!isPasswordConfirmed(userDto)) {
            return false; // Password confirmation failed
        }

        // Check for email duplication using JpaRepository
        if (emailCheck(userDto.getEmail())) {
            return false; // Duplicate email
        }

        // Check for nickname duplication using JpaRepository
        if (nicknameCheck(userDto.getNickname())) {
            return false; // Duplicate nickname
        }

        // Create and save the user entity
        User user = new User();
        user.setProfileImage(userDto.getProfileImage());
        user.setNickname(userDto.getNickname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setInterest(userDto.getInterest());
        user.setCheckIn(false);
        user.setPoint(0);
        user.setPassTicket(0);

        userRepository.save(user);
        return true; // Successful registration
    }

    public boolean emailCheck(String email) {
        return userRepository.findEmailByEmail(email) != null;
    }

    public boolean nicknameCheck(String nickname) {
        return userRepository.findNicknameByNickname(nickname) != null;
    }
    @Override
    public String findUserEmailByEmail(String email) {
        return userRepository.findEmailByEmail(email);
    }




    @Override
    public boolean isPasswordConfirmed(UserDto userDto) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByUserId(Long userId) {
        //수정한 부분
        return userRepository.findByUserId(userId);
    }

    @Override
    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new LoginResponse(false, "USER_NOT_FOUND", null);
        } else if (!user.getPassword().equals(password)) {
            return new LoginResponse(false, "INVALID_PASSWORD", null);
        }
        return new LoginResponse(true, null, user);
    }
    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

            // 해당 User가 관리자로 있는 JaraUs 엔티티들 조회
            Set<JaraUs> administeredJaraUses = user.getAdministeredJaraUses();
            if (administeredJaraUses != null && !administeredJaraUses.isEmpty()) {
                for (JaraUs jaraUs : administeredJaraUses) {
                    // 각 JaraUs의 관리자 정보를 null로 설정하거나 새로운 관리자를 지정
                    jaraUs.setAdministrator(null); // null로 설정
                    jaraUsRepository.save(jaraUs); // 변경 사항 저장
                }
            }

            // User 엔티티 삭제
            userRepository.delete(user);
        } catch (Exception e) {
            // 로깅 라이브러리를 사용하여 예외 로그를 출력
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public String updateProfileImage(Long userId, MultipartFile image) throws IOException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
         // 파일 형식 검사
        String contentType = image.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            throw new RuntimeException("Invalid image format. Only JPG and PNG are allowed.");
        }

        String imageUrl = uploadImage(image); // 이미지 업로드 로직
        user.setProfileImage(imageUrl);
        userRepository.save(user);

        return imageUrl;
    }

    private String uploadImage(MultipartFile image) throws IOException {
        String uploadDir = "uploads/images";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 이미지 파일 이름 생성 및 저장 로직
        String fileName = generateUniqueFileName(image.getOriginalFilename());
        Path imagePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        return imagePath.toString();
    }

    private String generateUniqueFileName(String originalFilename) {
        // 타임스탬프를 이용한 고유 식별자 생성
        String timestamp = String.valueOf(Instant.now().toEpochMilli());

        // 원본 파일의 확장자를 유지하기 위한 처리
        String extension = "";
        int extensionIndex = originalFilename.lastIndexOf('.');
        if (extensionIndex > 0) {
            extension = originalFilename.substring(extensionIndex); // 파일 확장자 포함
            originalFilename = originalFilename.substring(0, extensionIndex);  // 확장자를 제외한 파일 이름만 추출
        }

        // 고유한 파일 이름 생성
        return originalFilename + "_" + timestamp + extension;
    }

    @Override
    public boolean changeNickname(Long userId, String newNickname, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (userRepository.findByNickname(newNickname).isPresent()) {
            return false; // 닉네임 중복
        }

        user.setNickname(newNickname);
        userRepository.save(user);
        return true; // 닉네임 변경 성공
    }

    public int getPassTicket(Long userId) {
        return userRepository.findPassTicketByUserId(userId);
    }
    @Override
    public int getPoints(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getPoint();
    }

    @Override
    public int getParticipatingJaraUsCount(Long userId) {
        return joinUsersRepository.findJaraUs_jaraUsIdsByUser_userId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .size();
    }
}
