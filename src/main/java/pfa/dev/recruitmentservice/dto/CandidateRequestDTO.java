package pfa.dev.recruitmentservice.dto;
import lombok.Data;

@Data
public class CandidateRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String portfolioUrl;
    private Integer yearsOfExperience;
    private String currentCompany;
    private String currentPosition;
    private String cvPath;
}