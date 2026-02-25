package pfa.dev.recruitmentservice.dto;
import lombok.Data;

@Data
public class AttachmentResponseDTO {

    private Long id;
    private Long candidateId;
    private String fileName;
    private String fileType;
    private String filePath;
}