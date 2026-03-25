package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.entities.Attachment;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.mapper.AttachmentMapper;
import pfa.dev.recruitmentservice.repositories.AttachmentRepopsitory;
import pfa.dev.recruitmentservice.repositories.CandidateRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepopsitory attachmentRepository;
    private final CandidateRepository candidateRepository;
    private final AttachmentMapper attachmentMapper;
    private final ClamavScanService clamavScanService;

    @Override
    public AttachmentResponseDTO createAttachment(AttachmentRequestDTO dto) {
        try {
            Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));

            MultipartFile file = dto.getFile();
            byte[] fileBytes = file.getBytes();
            clamavScanService.scan(file.getOriginalFilename(), fileBytes);
            Attachment attachment = attachmentMapper.toEntity(dto);

            attachment.setCandidate(candidate);
            attachment.setFileData(file.getBytes());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFileType(file.getContentType());

            attachmentRepository.save(attachment);

            return attachmentMapper.toDTO(attachment);

        } catch (IOException e) {
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Override
    public AttachmentResponseDTO updateAttachment(Long id, AttachmentRequestDTO dto) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        attachmentMapper.updateEntity(dto, attachment);

        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            try {
                byte[] fileBytes = dto.getFile().getBytes();

                clamavScanService.scan(dto.getFile().getOriginalFilename(), fileBytes);

                attachment.setFileData(dto.getFile().getBytes());
                attachment.setFileName(dto.getFile().getOriginalFilename());
                attachment.setFileType(dto.getFile().getContentType());
            } catch (IOException e) {
                throw new RuntimeException("File update failed", e);
            }
        }

        attachmentRepository.save(attachment);

        return attachmentMapper.toDTO(attachment);
    }

    @Override
    public void deleteAttachment(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
        attachmentRepository.delete(attachment);
    }

    @Override
    public AttachmentResponseDTO getAttachmentById(Long id) {
        return attachmentRepository.findById(id)
                .map(attachmentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }

    @Override
    public Page<AttachmentResponseDTO> getAllAttachmentsByCandidate(Long candidateId, Pageable pageable) {
        return attachmentRepository.findByCandidate_Id(candidateId, pageable)
                .map(attachmentMapper::toDTO);
    }




    @Override
    public Resource downloadFile(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        return new ByteArrayResource(attachment.getFileData());
    }
}