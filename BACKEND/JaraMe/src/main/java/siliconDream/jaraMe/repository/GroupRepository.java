package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.Group;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsByPath(String path);

    @Query("SELECT g FROM Group g WHERE g.endDate = :today")
    List<Group> findEndDateToday(LocalDate today);

}
