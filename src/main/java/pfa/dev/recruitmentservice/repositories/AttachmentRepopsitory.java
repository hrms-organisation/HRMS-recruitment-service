package pfa.dev.recruitmentservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.recruitmentservice.entities.Attachment;
import pfa.dev.recruitmentservice.entities.Candidate;

public interface AttachmentRepopsitory extends JpaRepository<Attachment, Long> {
    Page<Attachment> findByCandidate(
            Candidate candidate,
            Pageable pageable
    );
}
