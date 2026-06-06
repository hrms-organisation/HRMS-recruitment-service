package pfa.dev.recruitmentservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 2000)
    private String description;
    private String requiredSkills;
    private String experienceLevel;
    private String employmentType;
    private Double salaryMin;
    private Double salaryMax;
    private String location;
    private String status;


    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Application> applications;

}