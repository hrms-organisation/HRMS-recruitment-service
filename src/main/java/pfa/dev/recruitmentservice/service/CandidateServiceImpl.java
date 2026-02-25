package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pfa.dev.recruitmentservice.dto.CandidateRequestDTO;
import pfa.dev.recruitmentservice.dto.CandidateResponseDTO;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.exception.ResourceNotFoundException;
import pfa.dev.recruitmentservice.mapper.CandidateMapper;
import pfa.dev.recruitmentservice.repositories.CandidateRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    @Override
    public CandidateResponseDTO createCandidate(CandidateRequestDTO dto) {
       existCandidate(dto.getEmail());

        Candidate candidateEntity = candidateMapper.toEntity(dto);
        candidateRepository.save(candidateEntity);
        return candidateMapper.toDTO(candidateEntity);


    }

    @Override
    public CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO dto) {
        Candidate existCnadidate = findCandidateById(id);


        candidateMapper.updateCandidateFromDto(dto, existCnadidate);
       Candidate updateCandidate = candidateRepository.save(existCnadidate);


        return candidateMapper.toDTO(updateCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = findCandidateById(id);
        candidateRepository.delete(candidate);


    }

    @Override
    public CandidateResponseDTO getCandidateById(Long id) {
        Candidate candidate = findCandidateById(id);

        return candidateMapper.toDTO(candidate);
    }

    @Override
    public Page<CandidateResponseDTO> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable).map(candidateMapper::toDTO);



    }

    private Candidate findCandidateById(Long id) {
        Candidate candidate =  candidateRepository.existCandidate(id);
        if(candidate == null) {
            throw new ResourceNotFoundException("candidate not found");
        }
        return candidate;
    }
    private void existCandidate(String email ) {
        if (candidateRepository.existsCandidateByEmail(email)) {
            throw new ResourceAlreadyExistException("Candidate already exists with email: " + email);
        }

    }
}
