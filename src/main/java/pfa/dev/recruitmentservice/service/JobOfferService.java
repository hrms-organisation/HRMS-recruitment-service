package pfa.dev.recruitmentservice.service;

import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;

import java.util.List;

public interface JobOfferService {

    JobOfferResponseDTO createJobOffer(JobOfferRequestDTO dto);

    JobOfferResponseDTO updateJobOffer(Long id, JobOfferRequestDTO dto);

    void deleteJobOffer(Long id);

    JobOfferResponseDTO getJobOfferById(Long id);

    List<JobOfferResponseDTO> getAllJobOffers();
}
