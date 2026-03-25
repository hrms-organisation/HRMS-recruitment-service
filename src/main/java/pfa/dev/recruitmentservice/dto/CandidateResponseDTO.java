package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CandidateResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String portfolioUrl;



    private Integer yearsOfExperience;
    private String currentCompany;
    private String currentPosition;
    private List<AttachmentResponseDTO> attachments;



}