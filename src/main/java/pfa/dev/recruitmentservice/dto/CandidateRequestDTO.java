package pfa.dev.recruitmentservice.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
}