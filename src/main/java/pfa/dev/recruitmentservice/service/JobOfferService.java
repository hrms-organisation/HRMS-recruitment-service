package pfa.dev.recruitmentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;

public interface JobOfferService {

    JobOfferResponseDTO createJobOffer(JobOfferRequestDTO dto);

    JobOfferResponseDTO updateJobOffer(Long id, JobOfferRequestDTO dto);

    void deleteJobOffer(Long id);

    JobOfferResponseDTO getJobOfferById(Long id);

    Page<JobOfferResponseDTO> getAllJobOffers(Pageable pageable);
}
