package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.repository.JoinUsersRepository;

import java.util.List;

@Service
public class JoinUsersServiceImpl implements JoinUsersService {
    private final JoinUsersRepository joinUsersRepository;
    public JoinUsersServiceImpl(JoinUsersRepository joinUsersRepository){
        this.joinUsersRepository=joinUsersRepository;
    }

    public List<Long> findUserIdsByJaraUsId(Long jaraUsId){
        return joinUsersRepository.findUserIdsByJaraUs_JaraUsId(jaraUsId);
    }
}
