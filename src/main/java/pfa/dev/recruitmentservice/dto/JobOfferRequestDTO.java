package pfa.dev.recruitmentservice.dto;

import lombok.Data;

@Data
public class JobOfferRequestDTO {

    private String title;
    private String description;
    private String requiredSkills;
    private String experienceLevel;
    private String employmentType;
    private Double salaryMin;
    private Double salaryMax;
    private String location;
    private Long organisationId;
}