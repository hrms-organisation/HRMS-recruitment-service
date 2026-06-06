package pfa.dev.recruitmentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.recruitmentservice.dto.ApplicationRequestDTO;
import pfa.dev.recruitmentservice.dto.ApplicationResponseDTO;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDTO createApplication(ApplicationRequestDTO dto);

    ApplicationResponseDTO updateApplicationStatus(Long id, String status);

    void deleteApplication(Long id);

    ApplicationResponseDTO getApplicationById(Long id);

    Page<ApplicationResponseDTO> getAllApplications(Pageable pageable);

    Page<ApplicationResponseDTO> getApplicationsByCandidate(Long candidateId, Pageable pageable);
}
