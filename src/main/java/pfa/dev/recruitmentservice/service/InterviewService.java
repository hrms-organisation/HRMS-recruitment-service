package pfa.dev.recruitmentservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.recruitmentservice.dto.InterviewRequestDTO;
import pfa.dev.recruitmentservice.dto.InterviewResponseDTO;

public interface InterviewService {

    // schedule interview
    InterviewResponseDTO scheduleInterview(
            InterviewRequestDTO dto
    );

    // update interview
    InterviewResponseDTO updateInterview(
            Long id,
            InterviewRequestDTO dto
    );

    // delete
    void deleteInterview(Long id);

    // get single
    InterviewResponseDTO getInterviewById(Long id);

    Page<InterviewResponseDTO> getAllInterviews(Pageable pageable);

    // pagination by application
    Page<InterviewResponseDTO>
    getInterviewsByApplication(
            Long applicationId,
            Pageable pageable
    );

}
