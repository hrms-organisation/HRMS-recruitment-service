package pfa.dev.recruitmentservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Page<Interview> findByApplication(
            Application application,
            Pageable pageable
    );

    boolean existsByApplication(Application application);

}
