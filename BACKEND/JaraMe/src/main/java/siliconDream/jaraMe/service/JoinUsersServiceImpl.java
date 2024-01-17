package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.repository.JoinUsersRepository;

import java.util.List;

@Service
public class JoinUsersServiceImpl {
    private final JoinUsersRepository joinUsersRepository;
    public JoinUsersServiceImpl(JoinUsersRepository joinUsersRepository){
        this.joinUsersRepository=joinUsersRepository;
    }

    public List<Long> findUserIdsByGroupId(Long groupId){
        return joinUsersRepository.findUserIdsByGroupId(groupId);
    }
}
