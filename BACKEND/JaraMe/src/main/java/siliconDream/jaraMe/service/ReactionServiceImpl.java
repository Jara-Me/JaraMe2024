package siliconDream.jaraMe.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.Reaction;
import siliconDream.jaraMe.dto.MissionReactionDTO;
import siliconDream.jaraMe.repository.MissionPostRepository;
import siliconDream.jaraMe.repository.ReactionRepository;
import siliconDream.jaraMe.repository.UserRepository;

import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final UserRepository userRepository;
    private final MissionPostRepository missionPostRepository;
    private final ReactionRepository reactionRepository;

    public ReactionServiceImpl(UserRepository userRepository,
                               MissionPostRepository missionPostRepository,
                               ReactionRepository reactionRepository) {

        this.userRepository = userRepository;
        this.missionPostRepository = missionPostRepository;
        this.reactionRepository = reactionRepository;
    }

    public ResponseEntity<String> addReaction(MissionReactionDTO missionReactionDTO, Long userId) {
        //TODO: 예외처리 : 존재하는 리액션 타입이 맞는지?

        Optional<Reaction> reactionOptional=reactionRepository.findReactionByMissionPost_MissionPostIdAndUser_UserId(missionReactionDTO.getMissionPostId(), userId);
        if (reactionOptional.isEmpty()){
            Reaction reaction = new Reaction();
            reaction.setUser(userRepository.findByUserId(userId));
            reaction.setMissionPost(missionPostRepository.findByMissionPostId(missionReactionDTO.getMissionPostId()));
            reaction.setReactionType(missionReactionDTO.getReactionType());

            reactionRepository.save(reaction);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("%s를 누르셨습니다!",missionReactionDTO.getReactionType()));
        }else if (reactionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( String.format("이미 %s를 누르셨습니다.",reactionOptional.get().getReactionType()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("리액션을 누를 수 없습니다.");

    }

    public String deleteReaction(MissionReactionDTO missionReactionDTO, Long userId) {

        Optional<Reaction> reactionOptional=reactionRepository.findReactionByMissionPost_MissionPostIdAndUser_UserId(missionReactionDTO.getMissionPostId(), userId);
        if (reactionOptional.isPresent()){

            reactionRepository.delete(reactionOptional.get());
            return "리액션이 취소되었습니다.";

            //삭제가 됐는지 점검하는 부분

        }   Optional<Reaction> deleteTestReactionOptional = reactionRepository.findByReactionId(reactionOptional.get().getReactionId());
        if (deleteTestReactionOptional.isPresent()){return "리액션 취소에 실패했습니다.";            }
    return "리액션 취소에 실패했습니다.";
    }
}
