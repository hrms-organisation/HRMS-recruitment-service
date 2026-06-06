package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pfa.dev.recruitmentservice.dto.ApplicationRequestDTO;
import pfa.dev.recruitmentservice.dto.ApplicationResponseDTO;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Candidate;
import pfa.dev.recruitmentservice.entities.JobOffer;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    // DTO de réponse - Entity vers DTO
    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(source = "jobOffer.id", target = "jobOfferId")
    ApplicationResponseDTO toDTO(Application application);

    // DTO de requête - DTO vers Entity
    // Note: candidate et jobOffer nécessitent une conversion manuelle des IDs
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", source = "candidateId")
    @Mapping(target = "jobOffer", source = "jobOfferId")
    @Mapping(target = "status", constant = "APPLIED")
    @Mapping(target = "appliedDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "interviews", ignore = true)
    Application toEntity(ApplicationRequestDTO dto);

    // Méthodes de conversion pour les entités liées
    default Candidate mapCandidateId(Long candidateId) {
        if (candidateId == null) return null;
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);
        return candidate;
    }

    default JobOffer mapJobOfferId(Long jobOfferId) {
        if (jobOfferId == null) return null;
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(jobOfferId);
        return jobOffer;
    }
}