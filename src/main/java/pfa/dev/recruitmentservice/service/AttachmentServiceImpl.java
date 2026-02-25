package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.entities.Attachment;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.exception.ResourceNotFoundException;
import pfa.dev.recruitmentservice.mapper.AttachmentMapper;
import pfa.dev.recruitmentservice.repositories.AttachmentRepopsitory;
import pfa.dev.recruitmentservice.repositories.CandidateRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {

private final AttachmentRepopsitory attachmentRepository;
    private final CandidateRepository candidateRepository;

    private final AttachmentMapper attachmentMapper;



    // ================= CREATE =================

    @Override
    @Transactional
    public AttachmentResponseDTO
    createAttachment(AttachmentRequestDTO dto) {

        log.info("Creating attachment for candidate {}",
                dto.getCandidateId());

        Candidate candidate =
                findCandidate(dto.getCandidateId());

        Attachment attachment =
                attachmentMapper.toEntity(dto);

        attachment.setCandidate(candidate);

        Attachment saved =
                attachmentRepository.save(attachment);

        return attachmentMapper.toDTO(saved);
    }


    // ================= UPDATE =================

    @Override
    @Transactional
    public AttachmentResponseDTO updateAttachment(
            Long id,
            AttachmentRequestDTO dto) {

        Attachment attachment =
                findAttachment(id);

        attachmentMapper.updateEntity(
                dto,
                attachment
        );

        Attachment updated =
                attachmentRepository.save(attachment);

        log.info("Attachment updated {}", id);

        return attachmentMapper.toDTO(updated);
    }


    // ================= DELETE =================

    @Override
    @Transactional
    public void deleteAttachment(Long id) {

        Attachment attachment =
                findAttachment(id);

        attachmentRepository.delete(attachment);

        log.info("Attachment deleted {}", id);
    }


    // ================= GET BY ID =================

    @Override
    public AttachmentResponseDTO
    getAttachmentById(Long id) {

        Attachment attachment =
                findAttachment(id);

        return attachmentMapper.toDTO(attachment);
    }


    // ================= GET BY CANDIDATE =================

    @Override
    public Page<AttachmentResponseDTO>
    getAllAttachmentsByCandidate(
            Long candidateId,
            Pageable pageable) {

        Candidate candidate =
                findCandidate(candidateId);

        return attachmentRepository
                .findByCandidate(candidate, pageable)
                .map(attachmentMapper::toDTO);
    }

    // ================= PRIVATE =================

    private Attachment findAttachment(Long id) {

        return attachmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attachment not found id : " + id
                        ));
    }

    private Candidate findCandidate(Long id) {

        return candidateRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found id : " + id
                        ));
    }

}
