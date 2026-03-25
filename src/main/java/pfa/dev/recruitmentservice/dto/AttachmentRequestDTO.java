package pfa.dev.recruitmentservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AttachmentRequestDTO {

    private Long candidateId;      // ID du candidat
    private MultipartFile file;    // fichier CV réel
    private String category;
}