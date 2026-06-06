package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.dev.recruitmentservice.dto.CandidateRequestDTO;
import pfa.dev.recruitmentservice.dto.CandidateResponseDTO;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.exception.ResourceNotFoundException;
import pfa.dev.recruitmentservice.mapper.CandidateMapper;
import pfa.dev.recruitmentservice.repositories.CandidateRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    // ================= CREATE =================

    @Override
    @Transactional
    public CandidateResponseDTO createCandidate(CandidateRequestDTO dto) {

        if(candidateRepository.existsByEmail(dto.getEmail())){
            throw new ResourceAlreadyExistException(
                    "Candidate already exists with email : " + dto.getEmail()
            );
        }

        Candidate candidate = candidateMapper.toEntity(dto);

        Candidate savedCandidate = candidateRepository.save(candidate);

        return candidateMapper.toDTO(savedCandidate);
    }

    // ================= UPDATE =================

    @Override
    @Transactional
    public CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO dto) {

        Candidate candidate = findCandidate(id);

        candidateMapper.updateCandidateFromDto(dto, candidate);

        Candidate updatedCandidate = candidateRepository.save(candidate);

        return candidateMapper.toDTO(updatedCandidate);
    }

    // ================= DELETE =================

    @Override
    @Transactional
    public void deleteCandidate(Long id) {

        Candidate candidate = findCandidate(id);

        candidateRepository.delete(candidate);
    }

    // ================= GET BY ID =================

    @Override
    public CandidateResponseDTO getCandidateById(Long id) {

        Candidate candidate = findCandidate(id);

        return candidateMapper.toDTO(candidate);
    }

    // ================= GET ALL =================

    @Override
    public Page<CandidateResponseDTO> getAllCandidates(Pageable pageable) {

        return candidateRepository
                .findAll(pageable)
                .map(candidateMapper::toDTO);
    }

    // ================= PRIVATE =================

    private Candidate findCandidate(Long id){

        return candidateRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found id : " + id
                        ));
    }
}