package pfa.dev.recruitmentservice.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;

import java.io.IOException;

public interface AttachmentService {

    // Créer un attachment (upload fichier en DB)
    AttachmentResponseDTO createAttachment(AttachmentRequestDTO dto) throws IOException;

    // Mettre à jour un attachment (catégorie ou fichier)
    AttachmentResponseDTO updateAttachment(Long id, AttachmentRequestDTO dto) throws IOException;

    // Supprimer un attachment
    void deleteAttachment(Long id);

    // Récupérer un attachment par id
    AttachmentResponseDTO getAttachmentById(Long id);

    // Récupérer tous les attachments d'un candidat (avec pagination)
    Page<AttachmentResponseDTO> getAllAttachmentsByCandidate(Long candidateId, Pageable pageable);

    // Télécharger le fichier en BLOB
    Resource downloadFile(Long id) throws IOException;
}