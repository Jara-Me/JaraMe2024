package siliconDream.jaraMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.Sample;
import siliconDream.jaraMe.dto.sampleDTO;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample,Long> {


    default List<Sample> saveSample(sampleDTO sampleDto){
        Sample sample = new Sample();
        sample.setSampleId(sampleDto.getSampleId());
        sample.setSampleName(sampleDto.getSampleName());
        sample.setRecurrenceSet(sampleDto.getRecurrence());
        save(sample);
        Long sampleId = sample.getSampleId();
        List<Sample> savedSample = findBySampleId(sampleId);
        return savedSample;
    }
    List<Sample> findBySampleId(Long sampleId);
}
