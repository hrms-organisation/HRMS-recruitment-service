package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InterviewRequestDTO {

    private Long applicationId;
    private Long interviewerId;
    private LocalDateTime interviewDate;
    private String type;
}