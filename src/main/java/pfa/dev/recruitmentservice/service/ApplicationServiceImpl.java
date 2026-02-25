package pfa.dev.recruitmentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.dev.recruitmentservice.dto.ApplicationRequestDTO;
import pfa.dev.recruitmentservice.dto.ApplicationResponseDTO;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.entities.JobOffer;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.exception.ResourceNotFoundException;
import pfa.dev.recruitmentservice.mapper.ApplicationMapper;
import pfa.dev.recruitmentservice.repositories.ApplicationRepository;
import pfa.dev.recruitmentservice.repositories.CandidateRepository;
import pfa.dev.recruitmentservice.repositories.JobOfferRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
private final JobOfferRepository jobOfferRepository;

    private final CandidateRepository candidateRepository;


    @Override
     @Transactional
    public ApplicationResponseDTO createApplication(ApplicationRequestDTO dto) {


        log.info("Creating application for candidate {}",
                dto.getCandidateId());
        Candidate candidate = findCandidate(dto.getCandidateId());
        JobOffer jobOffer = findJobOffer(dto.getJobOfferId());

        if (applicationRepository
                .existsByCandidateAndJobOffer(
                        candidate,
                        jobOffer)) {

            throw new ResourceAlreadyExistException(
                    "Candidate already applied to this job offer");
        }

        Application application =
                applicationMapper.toEntity(dto);

        application.setCandidate(candidate);
        application.setJobOffer(jobOffer);
        application.setStatus("PENDING");

        Application saved =
                applicationRepository.save(application);

        log.info("Application created id {}",
                saved.getId());

        return applicationMapper.toDTO(saved);

    }

    @Override
    public ApplicationResponseDTO updateApplicationStatus(Long id, String status) {
        Application application =
                findApplication(id);

        application.setStatus(status);

        return applicationMapper
                .toDTO(applicationRepository.save(application));    }

    @Override
    @Transactional
    public void deleteApplication(Long id) {

        Application application =
                findApplication(id);

        applicationRepository.delete(application);

        log.info("Application deleted {}", id);
    }

    @Override
    public ApplicationResponseDTO getApplicationById(Long id) {
        return null;
    }

    @Override
    public Page<ApplicationResponseDTO>
    getApplicationsByCandidate(
            Long candidateId,
            Pageable pageable) {

        Candidate candidate =
                findCandidate(candidateId);

        return applicationRepository
                .findByCandidate(candidate, pageable)
                .map(applicationMapper::toDTO);
    }

    private Candidate findCandidate(Long id){
        return candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Candidate with id %s not found",id)));
    }
    private JobOffer findJobOffer(Long id) {
    return  jobOfferRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("JobOffer with id %s not found",id)));

    }

    private Application findApplication(Long id) {

        return applicationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found id : " + id));
    }


}
