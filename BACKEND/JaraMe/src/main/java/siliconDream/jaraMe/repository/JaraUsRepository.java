package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.JaraUs;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JaraUsRepository extends JpaRepository<JaraUs, Long> {
/*
    boolean existsByPath(String path);
*/
    List<JaraUs> findJaraUsByAdministrator(Long adminUserId);

    @Query("SELECT j FROM JaraUs j WHERE j.endDate < :today")
    List<JaraUs> findExpiredJaraUs(LocalDate today);
    @Query("SELECT j FROM JaraUs j WHERE j.endDate = :today")
    List<JaraUs> findEndDateToday(LocalDate today);

}
