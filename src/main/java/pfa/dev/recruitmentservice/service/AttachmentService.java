package pfa.dev.recruitmentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;

import java.util.List;

public interface AttachmentService {
    AttachmentResponseDTO createAttachment(AttachmentRequestDTO dto);

    AttachmentResponseDTO updateAttachment(Long id, AttachmentRequestDTO dto);

    void deleteAttachment(Long id);

    AttachmentResponseDTO getAttachmentById(Long id);

    Page<AttachmentResponseDTO> getAllAttachmentsByCandidate(Long candidateId , Pageable pageable);
}
