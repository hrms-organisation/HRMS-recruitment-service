package pfa.dev.recruitmentservice.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttachmentResponseDTO {

    private Long id;
    private Long candidateId;
    private String fileName;
    private String fileType;
    private String category;

    private String objectKey;
}