package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;
import pfa.dev.recruitmentservice.entities.JobOffer;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.mapper.JobOfferMapper;
import pfa.dev.recruitmentservice.repositories.JobOfferRepository;

import java.util.List;
@Service
@RequiredArgsConstructor

public class JobOfferServiceImpl implements JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    private final JobOfferMapper jobOfferMapper;


    @Override
    public JobOfferResponseDTO createJobOffer(JobOfferRequestDTO dto) {
     checkIfJobOfferExists(dto.getTitle());
     JobOffer jobOfferEntity = jobOfferMapper.toEntity(dto);
     jobOfferRepository.save(jobOfferEntity);


        return jobOfferMapper.toDTO(jobOfferEntity);
    }

    @Override
    public JobOfferResponseDTO updateJobOffer(Long id, JobOfferRequestDTO dto) {
        JobOffer jobOffer = findJobOfferById(id);
        jobOfferMapper.updateJobOfferFromDto(dto, jobOffer);
        jobOfferRepository.save(jobOffer);

        return jobOfferMapper.toDTO(jobOffer);
    }

    @Override
    public void deleteJobOffer(Long id) {
        JobOffer jobOffer = findJobOfferById(id);

        jobOfferRepository.deleteById(id);


    }

    @Override
    public JobOfferResponseDTO getJobOfferById(Long id) {
        JobOffer jobOffer = findJobOfferById(id);
        return jobOfferMapper.toDTO(jobOffer);


    }

    @Override
    public List<JobOfferResponseDTO> getAllJobOffers() {
        return List.of();
    }
    private JobOffer findJobOfferById(Long id) {
        return jobOfferRepository.findById(id).orElseThrow(() -> new ResourceAlreadyExistException("JobOffer not found"));
    }
    private void checkIfJobOfferExists(String title) {
        if (jobOfferRepository.existsByTitle(title)) {
            throw new ResourceAlreadyExistException("JobOffer already exists");
        }
    }
}
