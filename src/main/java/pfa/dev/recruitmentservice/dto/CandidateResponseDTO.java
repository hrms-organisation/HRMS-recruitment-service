package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CandidateResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String portfolioUrl;
    private String cvPath;
    private Integer yearsOfExperience;
    private String currentCompany;
    private String currentPosition;
}