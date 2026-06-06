package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.*;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;
import pfa.dev.recruitmentservice.entities.JobOffer;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface JobOfferMapper {

    JobOfferResponseDTO toDTO(JobOffer jobOffer);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status", constant = "DRAFT"),
            @Mapping(target = "applications", ignore = true)
    })
    JobOffer toEntity(JobOfferRequestDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "applications", ignore = true)
    })
    void updateJobOfferFromDto(JobOfferRequestDTO dto, @MappingTarget JobOffer jobOffer);
}
