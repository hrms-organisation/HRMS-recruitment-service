package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationResponseDTO {

    private Long id;
    private Long candidateId;
    private Long jobOfferId;
    private String status;
    private LocalDateTime appliedDate;
    private String notes;
}