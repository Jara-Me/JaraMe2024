package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.MissionHistory;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface MissionHistoryRepository extends JpaRepository<MissionHistory,Long> {

    Set<LocalDate> findMissionDateByUser_UserIdAndJaraUs_JaraUsId(Long userId, Long jaraUsId);
}
