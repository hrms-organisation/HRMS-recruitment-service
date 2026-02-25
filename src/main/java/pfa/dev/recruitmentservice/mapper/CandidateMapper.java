package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.*;
import pfa.dev.recruitmentservice.dto.CandidateRequestDTO;
import pfa.dev.recruitmentservice.dto.CandidateResponseDTO;
import pfa.dev.recruitmentservice.entities.Candidate;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CandidateMapper {


    @Mappings({

            @Mapping(source = "id", target = "id"),

            @Mapping(source = "firstName", target = "firstName"),

            @Mapping(source = "lastName", target = "lastName"),

            @Mapping(source = "email", target = "email"),

            @Mapping(source = "phone", target = "phone"),

            @Mapping(source = "linkedinUrl", target = "linkedinUrl"),

            @Mapping(source = "portfolioUrl", target = "portfolioUrl"),

            @Mapping(source = "cvPath", target = "cvPath"),

            @Mapping(source = "yearsOfExperience",
                    target = "yearsOfExperience"),

            @Mapping(source = "currentCompany",
                    target = "currentCompany"),

            @Mapping(source = "currentPosition",
                    target = "currentPosition")

    })
    CandidateResponseDTO toDTO(Candidate candidate);



    // ========= REQUEST DTO -> ENTITY (CREATE) =========

    @Mappings({

            @Mapping(target = "id", ignore = true),

            @Mapping(target = "applications", ignore = true),

            @Mapping(target = "attachments", ignore = true)

    })
    Candidate toEntity(CandidateRequestDTO dto);



    // ========= UPDATE ENTITY =========

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mappings({

            @Mapping(target = "id", ignore = true),

            @Mapping(target = "applications", ignore = true),

            @Mapping(target = "attachments", ignore = true)

    })
    void updateCandidateFromDto(
            CandidateRequestDTO dto,
            @MappingTarget Candidate candidate
    );

}
