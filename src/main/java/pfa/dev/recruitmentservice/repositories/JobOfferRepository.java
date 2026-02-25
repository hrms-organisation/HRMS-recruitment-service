package pfa.dev.recruitmentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.recruitmentservice.entities.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    boolean existsByTitle(String title);

}
