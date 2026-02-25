package pfa.dev.recruitmentservice.dto;

import lombok.Data;

@Data
public class AttachmentRequestDTO {

    private Long id; // pour update, optional pour create

    private Long candidateId;

    private String fileName;

    private String fileType;

    private String filePath;
}