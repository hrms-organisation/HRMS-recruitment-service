package pfa.dev.recruitmentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.recruitmentservice.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Boolean existsByEmail(String email);
}
