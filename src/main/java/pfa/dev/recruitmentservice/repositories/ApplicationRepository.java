package pfa.dev.recruitmentservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.entities.JobOffer;

public interface ApplicationRepository  extends JpaRepository<Application,Long> {
    boolean existsByCandidateAndJobOffer(Candidate candidate, JobOffer jobOffer);

    Page<Application> findByCandidate(
            Candidate candidate,
            Pageable pageable
    );
}