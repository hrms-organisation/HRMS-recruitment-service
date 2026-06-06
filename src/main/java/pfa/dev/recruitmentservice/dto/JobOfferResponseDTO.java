package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobOfferResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private String experienceLevel;
    private String employmentType;
    private Double salaryMin;
    private Double salaryMax;
    private String location;
    private String status;

}
