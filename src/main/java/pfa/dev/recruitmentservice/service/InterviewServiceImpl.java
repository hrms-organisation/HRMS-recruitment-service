package pfa.dev.recruitmentservice.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.dev.recruitmentservice.dto.InterviewRequestDTO;
import pfa.dev.recruitmentservice.dto.InterviewResponseDTO;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Interview;
import pfa.dev.recruitmentservice.exception.ResourceAlreadyExistException;
import pfa.dev.recruitmentservice.exception.ResourceNotFoundException;
import pfa.dev.recruitmentservice.mapper.InterviewMapper;
import pfa.dev.recruitmentservice.repositories.ApplicationRepository;
import pfa.dev.recruitmentservice.repositories.InterviewRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)


public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewMapper interviewMapper;


    // ================= SCHEDULE =================

    @Override
    @Transactional
    public InterviewResponseDTO
    scheduleInterview(InterviewRequestDTO dto) {

        log.info("Scheduling interview for application {}",
                dto.getApplicationId());

        Application application =
                findApplication(dto.getApplicationId());

        // empêcher double interview
        if (interviewRepository
                .existsByApplication(application)) {

            throw new ResourceAlreadyExistException(
                    "Interview already exists for this application"
            );
        }

        Interview interview =
                interviewMapper.toEntity(dto);

        interview.setApplication(application);

        Interview saved =
                interviewRepository.save(interview);

        log.info("Interview scheduled id {}",
                saved.getId());

        return interviewMapper.toDTO(saved);
    }


    // ================= UPDATE =================

    @Override
    @Transactional
    public InterviewResponseDTO updateInterview(
            Long id,
            InterviewRequestDTO dto) {

        Interview interview =
                findInterview(id);

        interviewMapper.updateEntity(dto, interview);

        Interview updated =
                interviewRepository.save(interview);

        log.info("Interview updated {}", id);

        return interviewMapper.toDTO(updated);
    }


    // ================= DELETE =================

    @Override
    @Transactional
    public void deleteInterview(Long id) {

        Interview interview =
                findInterview(id);

        interviewRepository.delete(interview);

        log.info("Interview deleted {}", id);
    }


    // ================= GET BY ID =================

    @Override
    public InterviewResponseDTO
    getInterviewById(Long id) {

        return interviewMapper
                .toDTO(findInterview(id));
    }

    @Override
    public Page<InterviewResponseDTO> getAllInterviews(Pageable pageable) {
        return interviewRepository.findAll(pageable).map(interviewMapper::toDTO);
    }


    // ================= GET BY APPLICATION =================

    @Override
    public Page<InterviewResponseDTO>
    getInterviewsByApplication(
            Long applicationId,
            Pageable pageable) {

        Application application =
                findApplication(applicationId);

        return interviewRepository
                .findByApplication(application, pageable)
                .map(interviewMapper::toDTO);
    }


    // ================= PRIVATE =================

    private Interview findInterview(Long id) {

        return interviewRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found id : " + id
                        ));
    }

    private Application findApplication(Long id) {

        return applicationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found id : " + id
                        ));
    }
}
