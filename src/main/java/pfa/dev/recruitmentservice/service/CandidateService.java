package pfa.dev.recruitmentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pfa.dev.recruitmentservice.dto.CandidateRequestDTO;
import pfa.dev.recruitmentservice.dto.CandidateResponseDTO;

import java.util.List;

public interface CandidateService {
    CandidateResponseDTO createCandidate(CandidateRequestDTO dto);

    CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO dto);

    void deleteCandidate(Long id);

    CandidateResponseDTO getCandidateById(Long id);

    Page<CandidateResponseDTO> getAllCandidates(Pageable pageable);


}
