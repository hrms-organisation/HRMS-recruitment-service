package pfa.dev.recruitmentservice.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InterviewResponseDTO {

    private Long id;
    private Long applicationId;
    private Long interviewerId;
    private LocalDateTime interviewDate;
    private String type;
    private String status;
    private String feedback;
    private Integer rating;
}