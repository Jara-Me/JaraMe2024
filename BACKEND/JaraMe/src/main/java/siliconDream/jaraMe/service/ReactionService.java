package siliconDream.jaraMe.service;

<<<<<<< HEAD
=======
import org.springframework.http.ResponseEntity;
>>>>>>> develop_hm
import siliconDream.jaraMe.dto.MissionReactionDTO;

public interface ReactionService {

<<<<<<< HEAD
    String addReaction(MissionReactionDTO missionReactionDTO, Long userId);
=======
    ResponseEntity<String> addReaction(MissionReactionDTO missionReactionDTO, Long userId);
>>>>>>> develop_hm
    String deleteReaction(MissionReactionDTO missionReactionDTO, Long userId);
}
