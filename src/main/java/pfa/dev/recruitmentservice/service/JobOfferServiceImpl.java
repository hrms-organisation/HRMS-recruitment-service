package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;
import pfa.dev.recruitmentservice.entities.JobOffer;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.mapper.JobOfferMapper;
import pfa.dev.recruitmentservice.repositories.JobOfferRepository;

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
    public Page<JobOfferResponseDTO> getAllJobOffers(Pageable pageable) {
        return jobOfferRepository.findAll(pageable).map(jobOfferMapper::toDTO);
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
