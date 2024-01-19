package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sampleId;

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    private String sampleName;

    public Long getSampleId() {
        return sampleId;
    }



    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public Set<Recurrence> getRecurrenceSet() {
        return recurrenceSet;
    }

    public void setRecurrenceSet(Set<Recurrence> recurrenceSet) {
        this.recurrenceSet = recurrenceSet;
    }

    @ElementCollection(targetClass = Recurrence.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "SampleRecurrence", joinColumns = @JoinColumn(name = "sampleId"))
    @Enumerated(EnumType.STRING)
    private Set<Recurrence> recurrenceSet;
}
