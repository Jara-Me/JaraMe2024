package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siliconDream.jaraMe.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsByPath(String path);
}
